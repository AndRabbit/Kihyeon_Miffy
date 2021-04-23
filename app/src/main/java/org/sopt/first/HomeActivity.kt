package org.sopt.first

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import org.sopt.first.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {
    private lateinit var binding : ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        val gitid = intent.getStringExtra("id")
//        val name = intent.getStringExtra("name")
//
//        binding.tvHomeProfileId.setText(gitid)
//        binding.tvHomeProfileName.setText(name)
        val repositoryListFragment = RepositoryListFragment()
        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.frag_home_repo, repositoryListFragment)
        transaction.commit()

        Log.d("Log_Home", "onCreate")

        initButtonClickEvent()
    }

    private fun initButtonClickEvent(){
        binding.btnHomeMore.setOnClickListener{
            val intent = Intent(this, UserInfoActivity::class.java)
            startActivity(intent)
        }
    }


    override fun onStart() {
        super.onStart()
        Log.d("Log_Home", "onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d("Log_Home", "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d("Log_Home", "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d("Log_Home", "onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("Log_Home", "onDestroy")
    }
}