package io.getstream.streamchat1.ui.auth

import android.os.Bundle
import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import io.getstream.chat.android.client.ChatClient
import io.getstream.chat.android.client.call.enqueue
import io.getstream.chat.android.client.models.User
import io.getstream.streamchat1.R
import io.getstream.streamchat1.data.StreamTokenApi
import io.getstream.streamchat1.data.UserExtra
import io.getstream.streamchat1.databinding.FragmentSetupProfileBinding
import io.getstream.streamchat1.ui.chat.ChatActivity
import io.getstream.streamchat1.ui.snackbar
import io.getstream.streamchat1.ui.startNewActivity

class SetupProfileFragment : Fragment(R.layout.fragment_setup_profile) {
    private lateinit var binding: FragmentSetupProfileBinding
    private lateinit var currentUser: FirebaseUser
    private val streamApi = StreamTokenApi()
    private val tokenProvider = StreamTokenProvider(streamApi)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSetupProfileBinding.bind(view)
        binding.buttonNext.isEnabled = false
        binding.editTextName.addTextChangedListener {
            binding.buttonNext.isEnabled = it?.isNotEmpty() == true
        }

        currentUser = FirebaseAuth.getInstance().currentUser ?: return

        binding.buttonNext.setOnClickListener {
            setupProfile()
        }
    }

    private fun setupProfile() {
        val user = User(
            currentUser.uid,
            extraData = mutableMapOf(
                UserExtra.NAME to binding.editTextName.text.toString().trim(),
                UserExtra.PHONE to currentUser.phoneNumber!!,
                UserExtra.IMAGE to UserExtra.DEFAULT_AVATAR
            )
        )

        ChatClient
            .instance()
            .connectUser(user, tokenProvider.getTokenProvider(currentUser.uid))
            .enqueue {  result ->
                if(result.isSuccess){
                    //User Connected to the BAckend Successfully
                    requireActivity().startNewActivity(ChatActivity::class.java)
                }else{
                    snackbar("${result.error().message}")
                }
            }
    }
}