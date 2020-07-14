package com.intellyze.recharge.ui.fragment
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.intellyze.recharge.R
import com.intellyze.recharge.databinding.FragmentLoginBinding
import com.intellyze.recharge.livedata.UserData
import com.intellyze.recharge.model.LoginData
import com.intellyze.recharge.model.LoginErrors
import com.intellyze.recharge.view.model.LogInViewModel


/**
 * A simple [Fragment] subclass.
 */
class LoginFragment : Fragment() {
    var logInViewModel: LogInViewModel? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding= DataBindingUtil.inflate(inflater,R.layout.fragment_login, container, false) as FragmentLoginBinding
        logInViewModel = ViewModelProvider(this).get(LogInViewModel::class.java)
        binding.fragment=this

        binding.errorModel = LoginErrors(null)
        binding.viewModel = logInViewModel
        binding.data = LoginData()
        return binding.root


    }




    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeData()
    }
    fun observeData(){
        logInViewModel?.getUserLiveData()?.observe(viewLifecycleOwner, Observer<UserData> { t: UserData? ->
            when(t?.getStatus()){
                UserData.UserStatus.ERROR->{
                    showMsg(t.getError()?.getErrorMessage())
                }
                UserData.UserStatus.LOGIN_SUCCESS->{
                    Toast.makeText(activity, "Login Success", Toast.LENGTH_LONG).show()
                    findNavController().navigate(R.id.action_loginFragment_to_homeFragment)

                }
            }
        })
    }

    fun showMsg(msg: String?){
        activity?.runOnUiThread(Runnable {
            Toast.makeText(activity, msg, Toast.LENGTH_LONG).show()
        })
    }


    override fun onResume() {
        super.onResume()
        activity?.invalidateOptionsMenu()
        activity?.actionBar?.hide()
    }
}
