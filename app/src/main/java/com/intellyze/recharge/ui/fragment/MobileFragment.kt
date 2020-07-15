package com.intellyze.recharge.ui.fragment

import android.app.AlertDialog
import android.app.ProgressDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.activity.addCallback
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.intellyze.OnItemSelctedListener
import com.intellyze.recharge.R
import com.intellyze.recharge.cloud.response.operators.OperatorResponce
import com.intellyze.recharge.database.model.DbOperator
import com.intellyze.recharge.database.model.DbPlans
import com.intellyze.recharge.databinding.FragmentMobileBinding
import com.intellyze.recharge.livedata.UserData
import com.intellyze.recharge.model.MobileRechargeData
import com.intellyze.recharge.model.RechargeErrors
import com.intellyze.recharge.utls.Utility
import com.intellyze.recharge.view.model.RechargeViewModel
import kotlinx.android.synthetic.main.fragment_mobile.*


/**
 * A simple [Fragment] subclass.
 */
class MobileFragment : Fragment(), OnItemSelctedListener {
    var viewModel: RechargeViewModel? = null
    var data: MobileRechargeData? = null
    var binding: FragmentMobileBinding? = null
    var errorModel: RechargeErrors? = null
    var type:String = "MOBILE"
fun selectOperator()
{

    if(type=="MOBILE")
    { viewModel?.getOperatorById("MOBILE")
    }else{
        viewModel?.getOperatorById("DTH")
    }
    viewModel?.dbOperators?.observe(viewLifecycleOwner, Observer<List<DbOperator>>() { operator ->
        val dialogFragment = OperatorDialogFragment(this)
        dialogFragment.setCancelable(true)
        dialogFragment.setData(operator)
        dialogFragment.setTitle(type)
        dialogFragment.show(childFragmentManager,"OperatorDialogFragment")
    })
}
    fun browsePlans(data: MobileRechargeData, errorModel: RechargeErrors)
    {
        viewModel?.browsePlans(data,errorModel)
        viewModel?.dbPlans?.observe(viewLifecycleOwner, Observer<List<DbPlans>>() { plans ->
            val dialogFragment = PlansDialogFragment(this)
            dialogFragment.setCancelable(true)
            dialogFragment.setData(plans)
            dialogFragment.show(childFragmentManager,"PlansDialogFragment")
        })

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_mobile,
            container,
            false
        ) as FragmentMobileBinding
        viewModel = ViewModelProvider(this).get(RechargeViewModel::class.java)
        data = MobileRechargeData()
        errorModel = RechargeErrors(null)
        binding?.fragment = this
        binding?.errorModel = errorModel
        binding?.viewModel = viewModel
        binding?.data = data
        return binding?.root

    }


    fun startProgress(
        message: String?
    ) { //        this.context = context;
        mProgressDialog = ProgressDialog(
            activity,
            ProgressDialog.STYLE_SPINNER
        )
        mProgressDialog!!.isIndeterminate = false
        mProgressDialog!!.setMessage("Loading...")
        mProgressDialog!!.setCancelable(false)
        activity?.runOnUiThread { mProgressDialog!!.show() }
        Log.e("BASE ACT", "ShowLoading: ")
    }
    fun stopProgress() {
        activity?.runOnUiThread { mProgressDialog!!.dismiss() }
        Log.e("BASE ACT", "cancelLoading: ")
    }
    protected var mProgressDialog: ProgressDialog? = null

    private fun showAlert(
        tittle: String,
        message: String
    ) {
        val builder =
            AlertDialog.Builder(activity)
        val customLayout: View = layoutInflater.inflate(R.layout.show_confirm_alert, null)
        builder.setView(customLayout)
        val heading_text = customLayout.findViewById<TextView>(R.id.heading_text)
        val message_text = customLayout.findViewById<TextView>(R.id.message_text)
        val ok_action = customLayout.findViewById<TextView>(R.id.ok_action)
        heading_text.text = tittle
        message_text.text = message
        val dialog = builder.create()
        dialog.show()
        ok_action.setOnClickListener { view: View? ->
            dialog.dismiss()
        }
    }

    fun doRecharge(data: MobileRechargeData, errorModel: RechargeErrors) {
        if(Utility.isNetworkAvailable(context))
        {
            startProgress("Recharge processing Please wait")
            viewModel?.doRecharge(data,errorModel)
        }else{
            showAlert("No internet","Internet not available")
        }

    }


        fun observeData() {

        viewModel?.dbPlans?.observe(viewLifecycleOwner, Observer<List<DbPlans>>() { plans ->
            val dialogFragment = PlansDialogFragment(this)
            dialogFragment.setCancelable(true)
            dialogFragment.setData(plans)
            dialogFragment.show(childFragmentManager,"PlansDialogFragment")
        })


        viewModel?.getUserLiveData()
            ?.observe(viewLifecycleOwner, Observer<UserData> { t: UserData? ->
                when (t?.getStatus()) {
                    UserData.UserStatus.ERROR -> {
                        showMsg(t.getError()?.getErrorMessage())
                        stopProgress()
                    }
                    UserData.UserStatus.RECHARGE_SUCCESS -> {
                        stopProgress()
//                        Toast.makeText(activity, "Recharge Success", Toast.LENGTH_LONG).show()


                        showCustomAlertForSaveSummary("Success","Recharge Success")
//                        findNavController().navigate(R.id.action_mobileFragment_to_homeFragment)

                    }
                    UserData.UserStatus.RECHARGE_FAIL -> {
                        Toast.makeText(activity, t.getMessage(), Toast.LENGTH_LONG).show()
                        stopProgress()
                    }
                }
            })
    }


    private fun showCustomAlertForSaveSummary(
        tittle: String,
        message: String
    ) {
        val builder =
            AlertDialog.Builder(activity)
        val customLayout: View = layoutInflater.inflate(R.layout.show_confirm_alert, null)
        builder.setView(customLayout)
        val heading_text = customLayout.findViewById<TextView>(R.id.heading_text)
        val message_text = customLayout.findViewById<TextView>(R.id.message_text)
        val ok_action = customLayout.findViewById<TextView>(R.id.ok_action)
        heading_text.text = tittle
        message_text.text = message
        val dialog = builder.create()
        dialog.show()
        ok_action.setOnClickListener { view: View? ->
                                    findNavController().navigate(R.id.action_mobileFragment_to_homeFragment)
            dialog.dismiss()
        }
    }


    fun showMsg(msg: String?) {
        activity?.runOnUiThread(Runnable {
            Toast.makeText(activity, msg, Toast.LENGTH_LONG).show()
        })
    }


    var call: OnBackPressedCallback? = null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var rechargeType = arguments?.getInt("RECHARGE_TYPE")!!
        data?.operatorType = rechargeType.toString()
        binding?.data = data
        when(rechargeType){
            1-> {
                text_phone.hint = "Mobile Number"
                type = "MOBILE"
                moble_text.text = "MOBILE RECHARGE"
            }

            2-> {
                moble_text.text = "DTH RECHARGE"
                type = "DTH"
                text_phone.hint = "Smart Card  Number"
            }

        }
        activity?.title = "Hit-$type Recharge"
        observeData()
        call = requireActivity().onBackPressedDispatcher.addCallback(this) {
            if (null != call) {
                if (call?.isEnabled!!) {
                    findNavController().navigate(R.id.action_mobileFragment_to_homeFragment)
                    call?.isEnabled = false
                    call?.remove()
                }

            }
        }
        text_phone.setOnFocusChangeListener { view, b ->
            if (!b) {
                if(Utility.isNetworkAvailable(context))
                {

                errorModel?.uiUpdate = true
//                Toast.makeText(activity, "Get operator", Toast.LENGTH_LONG).show()
                viewModel?.getOperators(text_phone.text.toString())
                viewModel?.getOperatorLiveData()
                    ?.observe(
                        viewLifecycleOwner,
                        Observer<OperatorResponce> { t: OperatorResponce? ->
                            if (t?.getStatus() == 0) {
//                                Toast.makeText(activity, "operator" + t?.name, Toast.LENGTH_LONG).show()
                                data?.operatorId = t?.operatorId.toString()
                                data?.operatorName = t?.name
                                if (null != t?.logoUrls) {
                                    data?.logoUrl = t?.logoUrls!!.get(0)
                                }
                                binding?.data = data
                                errorModel?.uiUpdate = false
                            } else if (t?.getStatus() == 10) {
//                                Toast.makeText(activity, "operator failed" + t?.name, Toast.LENGTH_LONG).show()
                                errorModel?.uiUpdate = false
//                                viewModel?.getOperators(text_phone.text.toString())
                            }

                        })
            }else{
                    showAlert("No internet","Internet not available")
                }
            }
        }

    }


    override fun onItemSelected(obj: Any) {
        if(obj is DbOperator)
        {
            data?.operatorId = obj?.operatorId.toString()
            data?.operatorName = obj?.name

            data?.logoUrl = obj?.logoUrls
            binding?.data = data
        }
        if (obj is DbPlans)
        {

            data?.amount = obj.price
            binding?.data = data

        }

    }

}
