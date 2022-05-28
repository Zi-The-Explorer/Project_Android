package com.afauzi.letsdo.main.view

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.afauzi.letsdo.R
import com.afauzi.letsdo.databinding.ActivityEditProfileBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.squareup.picasso.Picasso
import java.io.IOException
import java.util.*

class EditProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEditProfileBinding

    private lateinit var firebaseDatabase: FirebaseDatabase
    private lateinit var databaseReference: DatabaseReference
    private lateinit var auth: FirebaseAuth

    private lateinit var firebaseStorage: FirebaseStorage
    private lateinit var storageReference: StorageReference

    private lateinit var fillPath: Uri

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()
        firebaseDatabase = FirebaseDatabase.getInstance()
        firebaseStorage = FirebaseStorage.getInstance()
        storageReference = firebaseStorage.reference

        val user: FirebaseUser? = auth.currentUser
        val userId = user!!.uid

        getDataUser(userId)


    }

    override fun onStart() {
        super.onStart()

        binding.btnUploadPhoto.setOnClickListener {
            selectImage()
        }

        binding.btnEditSave.setOnClickListener {
            saveEditData()
        }

        btnBackAction()
    }

    private fun getDataUser(userId: String) {
        databaseReference = firebaseDatabase.getReference("users").child(userId)
        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {

                if (snapshot.exists()) {
                    binding.tvNameEdit.text = snapshot.child("username").value.toString()
                    binding.tvEmailEdit.text = snapshot.child("email").value.toString()
                    val getImg = snapshot.child("photo_profile").value.toString()
                    Picasso.get().load(getImg).placeholder(R.drawable.avatar_example)
                        .into(binding.editImageProfile)

                    binding.etEditUsername.setText(snapshot.child("username").value.toString())
                    binding.etEditEmail.setText(snapshot.child("email").value.toString())
                } else {
                    val user: FirebaseUser? = auth.currentUser
                    user.let {
                        // Name, email address, and profile photo Url
                        val nameProv = user!!.displayName
                        val emailProv = user.email
                        val photoUrlProv = user.photoUrl

                        binding.tvNameEdit.text = nameProv
                        binding.tvEmailEdit.text = emailProv
                        Picasso.get().load(photoUrlProv).placeholder(R.drawable.avatar_example)
                            .into(binding.editImageProfile)

                        binding.etEditEmail.setText(emailProv)
                        binding.etEditUsername.setText(nameProv)

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
        binding.EditArrowBack.setOnClickListener {
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
                binding.editImageProfile.setImageBitmap(bitmap)
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }

    private fun saveEditData() {

        val user: FirebaseUser? = auth.currentUser
        val userId = user!!.uid

        databaseReference = FirebaseDatabase.getInstance().getReference("users").child(userId)

        val hashMap: HashMap<String, String> = HashMap()
        hashMap["user_id"] = userId
        hashMap["username"] = binding.etEditUsername.text.toString().trim()
        hashMap["email"] = binding.etEditEmail.text.toString().trim()

        databaseReference.setValue(hashMap).addOnCompleteListener(this) { task ->
            if (task.isSuccessful) {
                Toast.makeText(this, "Data kamu tersimpan", Toast.LENGTH_SHORT)
                    .show()
            } else {
                Toast.makeText(this, "Maaf Ada Kesalahan Penyimpanan data, Ini akan segera diperbaiki", Toast.LENGTH_SHORT).show()
            }
        }


        val uuid = UUID.randomUUID().toString()
        val storeRef: StorageReference = storageReference.child("profile_image/$uuid")

        storeRef.putFile(fillPath).addOnSuccessListener { taskSnapshot ->
            taskSnapshot.storage.downloadUrl.addOnSuccessListener { uri ->

                databaseReference = firebaseDatabase.getReference("users").child(userId).child("photo_profile")
                databaseReference.setValue(uri.toString()).addOnCompleteListener {
                    if (it.isSuccessful) {
                        Toast.makeText(
                            this,
                            "Photo profile berhasil deperbarui!",
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
    }

    companion object {
        private const val PICK_IMAGE_REQUEST: Int = 22
    }

}