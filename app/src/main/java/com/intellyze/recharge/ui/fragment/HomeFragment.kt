package com.intellyze.recharge.ui.fragment

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
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
                        showBalanceError("Wallet", "Please add wallet amount")
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
                        showBalanceError("Wallet", "Please add wallet amount")
                    }
                }
            }
        }else{
            showBalanceError("Wallet", "Please add wallet amount")
        }

    }

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
            showAlertDialog("Waring!", "Do you want you logout? ")
            return true
        }
        if (id == R.id.refresh) {
          observeData()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun showAlertDialog(title: String, message: String) {
        val mBuilder =
            AlertDialog.Builder(context)
        mBuilder.setTitle(title).setMessage(message)
        mBuilder.setPositiveButton(
            android.R.string.ok
        ) { dialogInterface, i ->

            homeViewModel?.logOut()
            dialogInterface.dismiss()
            findNavController().navigate(R.id.logout_to_loginFragment)


        }
        mBuilder.create()
        mBuilder.show()
    }

    private fun showBalanceError(title: String, message: String) {
        val mBuilder =
            AlertDialog.Builder(context)
        mBuilder.setTitle(title).setMessage(message)
        mBuilder.setPositiveButton(
            android.R.string.ok
        ) { dialogInterface, i ->
            dialogInterface.dismiss()
        }
        mBuilder.create()
        mBuilder.show()
    }

    override fun onItemClick(v: View, obj: Any) {
    }

    var name: String = ""
    var operatorId: String = ""
    var logoUrls: List<String>? = null
    fun observeData() {
        homeViewModel?.getTransactions()
        homeViewModel?.getWalletAmount()
        homeViewModel?.getTransactionLiveData()
            ?.observe(viewLifecycleOwner, Observer<Data> { t: Data? ->
                adapter?.setGroups(t?.transactions)

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

                })
    }


}
