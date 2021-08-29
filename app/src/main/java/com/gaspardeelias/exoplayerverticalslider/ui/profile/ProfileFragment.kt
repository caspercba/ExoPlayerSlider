package com.gaspardeelias.exoplayerverticalslider.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.gaspardeelias.exoplayerverticalslider.MainApplication
import com.gaspardeelias.exoplayerverticalslider.databinding.FragmentProfileBinding
import com.gaspardeelias.repo.Repository
import com.gaspardeelias.repo.model.User

class ProfileFragment : Fragment() {

    lateinit var repo: Repository
    val viewModel by viewModels<ProfileViewModel> { ProfileViewModelProvider(repo) }
    lateinit var binding: FragmentProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        repo = (requireActivity().application as MainApplication).getRepository()
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.data.observe(viewLifecycleOwner) {
            update(it)
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.refresh()
    }

    private fun update(user: User) {
        binding.username.text = user.userName
        binding.textVideos.text = "${user.userVideos} videos"
        binding.fansNumber.text = user.userFans.toString()
        binding.followingNumber.text = user.userFollowing.toString()
        binding.heartsNumber.text = user.userHearts.toString()
    }
}