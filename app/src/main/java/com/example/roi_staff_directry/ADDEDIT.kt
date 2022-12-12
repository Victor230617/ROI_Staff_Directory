package com.example.roi_staff_directry

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.addedit.*

class ADDEDIT: Activity(), View.OnClickListener {
    // create DBHandler object
    val dbh = DBHandler(this, null)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.addedit)
        // set on click listener
        btnSave.setOnClickListener(this)
        btnCancel.setOnClickListener(this)
        val extras = intent . extras
        if (extras != null) {
            //read and assign id from intent
            val id: Int = extras.getInt("ID")
            // get contact based on id
            val person = dbh.getPerson(id)
            //edit text populate
            etId.setText(person.id.toString())
            etName.setText(person.name)
            etPhone.setText(person.phone)
            etDepartment.setText(person.department)
            etStreet.setText(person.street)
            etCity.setText(person.city)
            etState.setText(person.state)
            etZip.setText(person.zip)
            etCountry.setText(person.country)
            ivImage.setImageResource(this.resources.getIdentifier(
                person.imageFile, "drawable","com.example.roi_staff_directry"))
        }
    }
    override fun onClick(btn: View) {
        // code to run for buttons
        when (btn.id) {
            R.id.btnSave -> {
                //Save Code
                // Read value from ID and put 0 if no value
                val cid: Int = try {
                    etId.text.toString().toInt()
                } catch (e: Exception) {
                    0
                }
                // decide add or update
                if (cid == 0) {
                    addContact()
                }else {
                    editContact(cid)
                }
            }
            R.id.btnCancel->{
                // cancel code
                goBack()
            }
        }
    }
    private fun editContact(cid:Int){
        // create contact object and fill new values
        val person = dbh.getPerson(cid)
        person.name = etName.text.toString()
        person.phone = etPhone.text.toString()
        person.department = etDepartment.text.toString()
        person.street = etStreet.text.toString()
        person.city = etCity.text.toString()
        person.state = etState.text.toString()
        person.zip = etZip.text.toString()
        person.country = etCountry.text.toString()
        person.imageFile = etImageFile.text.toString()
        // call dbhandler update function
        dbh.updatePerson(person)
        // display confirmation and go to Main Activity
        Toast.makeText(this,"Person has been updated",
            Toast.LENGTH_LONG).show()
        goBack()
    }
    private fun addContact() {
        // read values from edit text and assign to contact object
        val person = Person()
        person.name = etName.text.toString()
        person.phone = etPhone.text.toString()
        person.department = etDepartment.text.toString()
        person.street = etStreet.text.toString()
        person.city = etCity.text.toString()
        person.state = etState.text.toString()
        person.zip = etZip.text.toString()
        person.country = etCountry.text.toString()
        person.imageFile = etImageFile.text.toString()
        // Call dbhandler function to add
        dbh.addPerson(person)
        // display conformation
        Toast.makeText(this, "New Person created", Toast.LENGTH_LONG).show()
        goBack()
    }
    private fun goBack(){
        // go back to Main Activity
        val mainact = Intent(this,MainActivity::class.java)
        // to start another activity
        startActivity(mainact)
    }
}