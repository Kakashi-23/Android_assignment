package com.example.assignment.Activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.assignment.R
import com.example.assignment.Utils.Constants
import com.example.assignment.databinding.ActivityRepoInformationBinding

class RepoInformation : AppCompatActivity() {
    lateinit var repoInformationDataBiding:ActivityRepoInformationBinding
    private val list= Constants.objects.list
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
   //     setContentView(R.layout.activity_repo_information)
        repoInformationDataBiding=DataBindingUtil.setContentView(this, R.layout.activity_repo_information)
        val position=intent.getIntExtra("position", 0)
        repoInformationDataBiding.fullNameRI.setText(list[position].fullName)
        repoInformationDataBiding.ownerRI.setText(list[position].owner.login)
        repoInformationDataBiding.descriptionRI.setText(list[position].description)
        repoInformationDataBiding.saveRI.setOnClickListener {
            Constants.objects.isEdited=true
            Constants.objects.id.add(list[position].id)
            list[position].fullName=repoInformationDataBiding.fullNameRI.text.toString()
            list[position].owner.login=repoInformationDataBiding.ownerRI.text.toString()
            list[position].description=repoInformationDataBiding.descriptionRI.text.toString()
            val intent=Intent(this, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
            finish()
        }
        repoInformationDataBiding.navRI.setOnClickListener {
           super.onBackPressed()
        }
    }
}