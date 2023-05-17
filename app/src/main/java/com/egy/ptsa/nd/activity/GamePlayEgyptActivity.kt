package com.egy.ptsa.nd.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import com.egy.ptsa.nd.R
import com.egy.ptsa.nd.databinding.ActivityGamePlayEgyptBinding
import com.egy.ptsa.nd.listener.TouchListener
import com.egy.ptsa.nd.rv.AdapterPuzzle
import com.egy.ptsa.nd.view.dialog.GameOverDialog

class GamePlayEgyptActivity : AppCompatActivity() {
    private lateinit var binding: ActivityGamePlayEgyptBinding
    private val list = listOf(
        R.drawable.puzzle_01, R.drawable.puzzle_02, R.drawable.puzzle_03, R.drawable.puzzle_04,
        R.drawable.puzzle_05, R.drawable.puzzle_06, R.drawable.puzzle_07, R.drawable.puzzle_08,
        R.drawable.puzzle_09, R.drawable.puzzle_10, R.drawable.puzzle_11, R.drawable.puzzle_12,
        R.drawable.puzzle_13, R.drawable.puzzle_14, R.drawable.puzzle_15, R.drawable.puzzle_16,
    )
    private val adapter = AdapterPuzzle()
    private lateinit var currentList: MutableList<Int>
    private var score = 0
    private var life = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGamePlayEgyptBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.back.setOnClickListener { finish() }
        binding.rvGane.adapter = adapter
        binding.rvGane.setHasFixedSize(true)
        binding.rvGane.isNestedScrollingEnabled = false
        binding.rvGane.layoutManager = object : GridLayoutManager(this, 4) { override fun canScrollVertically() = false }
        currentList = mutableListOf()
        val itemTouchHelper = ItemTouchHelper(TouchListener(binding, this::onMove, this::onSwipe))

        startNewGame()
        itemTouchHelper.attachToRecyclerView(binding.rvGane)
        binding.submit.setOnClickListener { check() }
    }

    private fun onMove(fromPosition: Int, toPosition: Int){
        val item = currentList.removeAt(fromPosition)
        currentList.add(toPosition, item)
    }

    private fun startNewGame(){
        score = 0
        life = 3
        initGames()
    }

    private fun onSwipe(position: Int){
        currentList.remove(position)
    }

    private fun check(){
        var check = true
        for (i in list.indices){
            if (list[i] != currentList[i]){
                check = false
                break
            }
        }
        if (check){
//            Toast.makeText(this@GameActivity, "You Win", Toast.LENGTH_SHORT).show()
            scoreAdd()
//            initGames()
        } else {
            life--
            updateLife()
            if (life == 0){
                GameOverDialog{
                    startNewGame()
                }.show(supportFragmentManager, "go")
            }
        }
    }

    private fun scoreAdd(){
        score++
        updateScore()
        initGames()
    }

    private fun updateLife(){
        binding.lifeContainer.removeAllViews()
        (0 until life).forEach {
            val img = ImageView(this)
            img.adjustViewBounds = true
            img.setImageResource(R.drawable.life)
            val params = LinearLayout.LayoutParams(150, 150)
            img.layoutParams = params
            binding.lifeContainer.addView(img)
        }
    }

    private fun initGames(){
        currentList.clear()
        currentList.addAll(list.shuffled().toMutableList())
        adapter.updateList(currentList)
        updateScore()
        updateLife()
    }

    private fun updateScore(){
        binding.label.text = "$score"
    }
}