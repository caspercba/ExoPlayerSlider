package com.gaspardeelias.exoplayerverticalslider.ui.recycler

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gaspardeelias.exoplayerverticalslider.databinding.ItemRecyclerVideoBinding
import com.gaspardeelias.exoplayerverticalslider.utils.ExoPlayerUtils.buildMediaSource
import com.gaspardeelias.repo.model.Video
import com.google.android.exoplayer2.C.VIDEO_SCALING_MODE_SCALE_TO_FIT_WITH_CROPPING
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.ui.AspectRatioFrameLayout.RESIZE_MODE_FILL
import androidx.annotation.NonNull
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.gaspardeelias.exoplayerverticalslider.R
import com.google.android.exoplayer2.ui.StyledPlayerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.launch


class VideoRecyclerAdapter(context: Context, val eventBus: CustomEventBus): RecyclerView.Adapter<VideoRecyclerAdapter.VideoVH>() {

    private val videos: MutableList<Video> = mutableListOf()
    private var player:  SimpleExoPlayer = SimpleExoPlayer.Builder(context).build()
    private var oldPlayerView: StyledPlayerView? = null

    init {
        CoroutineScope(Dispatchers.Main).launch {
            eventBus.events.filter { it is RecyclerEvent.AttachPlayer }.collectLatest { event -> attachPlayer(event as RecyclerEvent.AttachPlayer) }
        }
    }

    fun attachPlayer(event: RecyclerEvent.AttachPlayer) {
        val playerView = event.view?.findViewById<StyledPlayerView>(R.id.player_view)
        val progress = event.view?.findViewById<View>(R.id.progress_circular)
        playerView?.let {
            it.isVisible = true
            progress?.isVisible = false
            StyledPlayerView.switchTargetView(player, oldPlayerView, playerView)
            player.pause()
            player.prepare(buildMediaSource(it.context, videos[event.pos].url))
        }
    }

    class VideoVH(private val binding: ItemRecyclerVideoBinding, private val player: SimpleExoPlayer):RecyclerView.ViewHolder(binding.root) {

        fun bind(video: Video) {
            binding.videoTitle.text = video.desc
            binding.progressCircular.isVisible = true
//            player.pause()
            binding.playerView.setPlayer(player)
//            binding.playerView.tag = url
//            player.apply {
//                prepare(buildMediaSource(itemView.context, url))
//                play()
//            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoVH {
        ItemRecyclerVideoBinding.inflate(LayoutInflater.from(parent.context), parent, false).also {
            return VideoVH(it, player)
        }
    }

    override fun onBindViewHolder(holder: VideoVH, position: Int) {
        holder.bind(videos[position])
    }

    override fun getItemCount() = videos.size

    fun setVideos(data: List<Video>) {
        videos.clear()
        videos.addAll(data)
        notifyDataSetChanged()
    }
}