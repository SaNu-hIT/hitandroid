package com.intellyze.recharge.ui.fragment

import android.app.AlertDialog
import android.app.ProgressDialog
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.intellyze.recharge.OnItemClickListener
import com.intellyze.recharge.R
import com.intellyze.recharge.cloud.response.transaction.Data
import com.intellyze.recharge.databinding.FragmentHomeBinding
import com.intellyze.recharge.ui.adapter.TransactionListAdapter
import com.intellyze.recharge.utls.Utility
import com.intellyze.recharge.view.model.HomeViewModel
import com.intellyze.recharge.view.model.LogInViewModel

/**
 * A simple [Fragment] subclass.
 */
class HomeFragment : Fragment(), OnItemClickListener {

    var logInViewModel: LogInViewModel? = null
    fun onItemClick(v: View) {

        if (null != binding?.wallet) {
            when (v.id) {
                R.id.mobile -> {
                    if (binding?.wallet?.walletBalance?.toLong()!! > 0L) {
                        var bundle = bundleOf("RECHARGE_TYPE" to 1)
                        findNavController().navigate(
                            R.id.action_homeFragment_to_mobileFragment,
                            bundle
                        )
                        return
                    } else {
                        showAlert("Wallet", "Please add wallet amount")
                    }
                }
                R.id.dth -> {
                    if (binding?.wallet?.walletBalance?.toLong()!! > 0L) {
                        var bundle = bundleOf("RECHARGE_TYPE" to 2)
                        findNavController().navigate(
                            R.id.action_homeFragment_to_mobileFragment,
                            bundle
                        )
                    } else {
                        showAlert("Wallet", "Please add wallet amount")
                    }
                }
            }
        }else{
            showAlert("Wallet", "Please add wallet amount")
        }

    }
    fun startProgress(
        message: String?
    ) {
        if (mProgressDialog==null) {
            mProgressDialog = ProgressDialog(
                activity,
                ProgressDialog.STYLE_SPINNER
            )
            mProgressDialog!!.isIndeterminate = false
            mProgressDialog!!.setMessage("Loading...")
            mProgressDialog!!.setCancelable(false)
        }
        activity?.runOnUiThread { mProgressDialog!!.show() }
        Log.e("BASE ACT", "ShowLoading: ")
    }
    fun stopProgress() {
        activity?.runOnUiThread { mProgressDialog!!.dismiss() }
        Log.e("BASE ACT", "cancelLoading: ")
    }
    protected var mProgressDialog: ProgressDialog? = null
    var binding: FragmentHomeBinding? = null
    var homeViewModel: HomeViewModel? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_home,
            container,
            false
        ) as FragmentHomeBinding
        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        binding?.fragment = this
        binding?.viewModel = homeViewModel
        return binding?.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView(view)
        activity?.title = "Hit-Recharge"
        (activity as AppCompatActivity).supportActionBar?.show()
    }
    var adapter: TransactionListAdapter? = null
    private fun initView(view: View) {
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        adapter = TransactionListAdapter(context)
        adapter?.onClick = this
        setHasOptionsMenu(true)
        recyclerView?.adapter = adapter
        recyclerView?.layoutManager = LinearLayoutManager(context)
        observeData()


    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_main, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean { // Handle action bar item clicks here. The action bar will
// automatically handle clicks on the Home/Up button, so long
// as you specify a parent activity in AndroidManifest.xml.
        val id: Int = item.getItemId()
        if (id == R.id.action_favorite) {
            showAlertDialog("Waring ! ", "Do you realy want you logout ? ")
            return true
        }else
        if (id == R.id.refresh) {
          observeData()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun showAlertDialog(title: String, message: String) {
        val builder =
            AlertDialog.Builder(activity)
        val customLayout: View = layoutInflater.inflate(R.layout.show_confirm_alert, null)
        builder.setView(customLayout)
        val heading_text = customLayout.findViewById<TextView>(R.id.heading_text)
        val message_text = customLayout.findViewById<TextView>(R.id.message_text)
        val ok_action = customLayout.findViewById<TextView>(R.id.ok_action)
        heading_text.text = title
        message_text.text = message
        val dialog = builder.create()
        dialog.show()
        ok_action.setOnClickListener { view: View? ->
            homeViewModel?.logOut()
            dialog.dismiss()
            findNavController().navigate(R.id.logout_to_loginFragment)



        }

    }



    override fun onItemClick(v: View, obj: Any) {
    }

    var name: String = ""
    var operatorId: String = ""
    var logoUrls: List<String>? = null
    fun observeData() {

        if(Utility.isNetworkAvailable(context))
        {
            startProgress("Loading..")
            homeViewModel?.getTransactions()

            homeViewModel?.getTransactionLiveData()
                ?.observe(viewLifecycleOwner, Observer<Data> { t: Data? ->
                    adapter?.setGroups(t?.transactions)

                    homeViewModel?.getWalletAmount()


                })
            homeViewModel?.getWalletLiveData()
                ?.observe(
                    viewLifecycleOwner,
                    Observer<com.intellyze.recharge.cloud.response.wallet.Data> { t: com.intellyze.recharge.cloud.response.wallet.Data? ->
                        if (null != t?.trans) {
                            if (t.trans?.size!! > 0) {
                                binding?.wallet = t.trans?.get(0)
                            }
                        }
                        stopProgress()

                    })
        }else{
            showAlert("No internet","Internet not available")
        }



    }
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
    override fun onResume() {
        super.onResume()


    }
}
