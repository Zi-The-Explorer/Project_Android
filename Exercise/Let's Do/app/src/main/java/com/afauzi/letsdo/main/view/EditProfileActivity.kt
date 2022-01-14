package com.afauzi.letsdo.main.view

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.afauzi.letsdo.R
import com.afauzi.letsdo.main.MainActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_edit_profile.*
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

class EditProfileActivity : AppCompatActivity() {

    private lateinit var firebaseDatabase: FirebaseDatabase
    private lateinit var databaseReference: DatabaseReference
    private lateinit var auth: FirebaseAuth

    private lateinit var calendar: Calendar
    private lateinit var simpleDateFormat: SimpleDateFormat

    private lateinit var firebaseStorage: FirebaseStorage
    private lateinit var storageReference: StorageReference

    private lateinit var fillPath: Uri
    private val PICK_IMAGE_REQUEST: Int = 22

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)

        auth = FirebaseAuth.getInstance()
        firebaseDatabase = FirebaseDatabase.getInstance()
        firebaseStorage = FirebaseStorage.getInstance()
        storageReference = firebaseStorage.reference

        val user: FirebaseUser? = auth.currentUser
        val userId = user!!.uid

        getDataUser(userId, tv_name_edit, tv_email_edit, edit_image_profile)

        btn_upload_photo.setOnClickListener {
            selectImage()
        }

        btnBackAction()
    }

    private fun getDataUser(
        userId: String,
        username: TextView,
        email: TextView,
        imageProfile: ImageView?
    ) {
        databaseReference = firebaseDatabase.getReference("users").child(userId)
        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {

                if (snapshot.exists()) {
                    tv_name_edit.text = snapshot.child("username").value.toString()
                    tv_email_edit.text = snapshot.child("email").value.toString()
                    val getImg = snapshot.child("photo_profile").value.toString()
                    Picasso.get().load(getImg).placeholder(R.drawable.avatar_example).into(edit_image_profile)
                } else {
                    val user: FirebaseUser? = auth.currentUser
                    user.let {
                        // Name, email address, and profile photo Url
                        val nameProv = user!!.displayName
                        val emailProv = user.email
                        val photoUrlProv = user.photoUrl

                        tv_name_edit.text = nameProv
                        tv_email_edit.text = emailProv
                        Picasso.get().load(photoUrlProv).placeholder(R.drawable.avatar_example).into(edit_image_profile)

                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }


    /**
     * Menangani Aksi kembali ke halaman sebelumnya
     */
    fun btnBackAction() {
        EditArrowBack.setOnClickListener {
            super.onBackPressed()
        }
    }

    fun selectImage() {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(
            Intent.createChooser(intent, "Select Image From Here"),
            PICK_IMAGE_REQUEST
        )
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK && data != null && data.data != null) {
            fillPath = data.data!!
            try {
                val bitmap: Bitmap =
                    MediaStore.Images.Media.getBitmap(this.contentResolver, fillPath)
                edit_image_profile.setImageBitmap(bitmap)

                val uuid = UUID.randomUUID().toString()
                val storeRef: StorageReference = storageReference.child("profile_image/$uuid")

                storeRef.putFile(fillPath).addOnSuccessListener { taskSnapshot ->
                    taskSnapshot.storage.downloadUrl.addOnSuccessListener { uri ->

                        val user: FirebaseUser? = auth.currentUser
                        val userId = user!!.uid
                        databaseReference = firebaseDatabase.getReference("users").child(userId)
                            .child("photo_profile")
                        databaseReference.setValue(uri.toString()).addOnCompleteListener {
                            if (it.isSuccessful) {
                                Toast.makeText(
                                    this,
                                    "Photo Profile Success Update",
                                    Toast.LENGTH_SHORT
                                ).show()
                            } else {
                                Toast.makeText(
                                    this,
                                    "error upload!",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                    }
                }.addOnFailureListener { e ->
                    print(e.message)
                    Toast.makeText(this, "Failed ${e.message}", Toast.LENGTH_SHORT).show()
                }
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }

}