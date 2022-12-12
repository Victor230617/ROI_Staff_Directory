package com.example.roi_staff_directry

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper


class DBHandler(context: Context, factory: SQLiteDatabase.CursorFactory?)
    : SQLiteOpenHelper(context, DATABASE_NAME, factory,DATABASE_VERSION ) {

    private val TableName: String = "Person"
    private val KeyID: String = "ID"
    private val KeyName: String = "NAME"
    private val KeyPhone: String = "PHONE"
    private val KeyDepartment: String = "DEPARTMENT"
    private val KeyStreet: String = "STREET"
    private val KeyCity: String = "CITY"
    private val KeyState: String = "STATE"
    private val KeyZip: String = "ZIP"
    private val KeyCountry: String = "COUNTRY"
    private val KeyImageFile: String = "IMAGEFILE"

    companion object {
        const val DATABASE_NAME = "HRManger.db"
        const val DATABASE_VERSION = 10
    }

    override fun onCreate(db: SQLiteDatabase) {
        // create sql statement for table
        val createTable: String = "CREATE TABLE $TableName" +
                "($KeyID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "$KeyName TEXT, $KeyImageFile TEXT, $KeyPhone Text, $KeyDepartment Text,"+
                "$KeyStreet Text, $KeyCity Text, $KeyState Text, $KeyZip Text, $KeyCountry Text)"
        // execute sql
        db.execSQL(createTable)
        // add one sample record using ContentValue Object
        val cv = ContentValues()
        // cv.put(KeyID, "1")
        cv.put(KeyName, "John Smith")
        cv.put(KeyImageFile, "second")
        cv.put(KeyPhone, "0299882211")
        cv.put(KeyDepartment, "1")
        cv.put(KeyStreet, "1 Code Lane")
        cv.put(KeyCity, "Javaville")
        cv.put(KeyState, "NSW")
        cv.put(KeyZip, "0100")
        cv.put(KeyCountry, "Australia")
        //use insert method
        db.insert(TableName, null, cv)

    }

    override fun onUpgrade(db: SQLiteDatabase, old: Int, new: Int) {
        //drop existing table
        db.execSQL("DROP TABLE IF EXISTS $TableName")
        //recreate database
        onCreate(db)
    }

    //get all the records from database
    fun getAllPersons(): ArrayList<Person> {
        //declare a arraylist to fill all records in contact object
        val personList = ArrayList<Person>()
        //create a sql query
        val selectQuery: String = "SELECT * FROM $TableName"
        //get   database for readable
        val db = this.readableDatabase
        // run query and put result in cursor
        val cursor = db.rawQuery(selectQuery, null)
        // move through cursor and read all the records
        if (cursor.moveToFirst()) {
            // LOOP TO READ ALL POSSIBLE RECORDS
            do {
                // create a contact object
                val person = Person()
                //read values from cursor and fill contact object
                person.id = cursor.getInt(0)
                person.name = cursor.getString(1)
                person.imageFile = cursor.getString(2)
                person.phone  = cursor.getString(3)
                person.department = cursor.getString(4)
                person.street = cursor.getString(5)
                person.city = cursor.getString(6)
                person.state  = cursor.getString(7)
                person.zip = cursor.getString(8)
                person.country = cursor.getString(9)
                // add contact object to array list
                personList.add(person)
            } while (cursor.moveToNext())
        }
        //close cursor and database
        cursor.close()
        db.close()
        // return arraylist of contact object
        return personList

    }
    // add new record in the DB
    fun addPerson(person: Person) {
        // get writable db
        val db = this.writableDatabase
        // create content value object
        val cv = ContentValues()
        cv.put(KeyName,person.name)
        cv.put(KeyImageFile,person.imageFile)
        cv.put(KeyPhone,person.phone)
        cv.put(KeyDepartment,person.department)
        cv.put(KeyStreet,person.street)
        cv.put(KeyCity,person.city)
        cv.put(KeyState,person.state)
        cv.put(KeyZip,person.zip)
        cv.put(KeyCountry,person.country)

        // use insert method
        db.insert(TableName,null, cv)
        // close db
        db.close()
    }
    //delete the existing record in DB by ID
    fun deletePerson(id: Int){
        // get writable db
        val db = this.writableDatabase
        db.delete(TableName,"$KeyID=?",
            arrayOf(id.toString()))
        // close db
        db.close()
    }
    // retrieve the record from DB passing ID

  fun getPerson(id: Int): Person {
      // get readable db
      val db = this.readableDatabase

      // create contact object to fill data
      val person = Person()

      // create cursor based on query
     val cursor =db.query(TableName,
         arrayOf(KeyID,KeyName,KeyImageFile,KeyPhone,KeyDepartment,KeyStreet,KeyCity,KeyState,KeyZip,KeyCountry),
         "$KeyID=?",
         arrayOf(id.toString()),
         null,
         null,
         null)
      // Check cursor and read value and put in contact
         if (cursor!= null){


          cursor.moveToFirst()
          person.id = cursor.getInt(0)
          person.name = cursor.getString(1)
          person.imageFile = cursor.getString(2)
          person.phone = cursor.getString(3)
          person.department = cursor.getString(4)
          person.street = cursor.getString(5)
          person.city = cursor.getString(6)
          person.state = cursor.getString(7)
          person.zip = cursor.getString(8)
          person.country = cursor.getString(9)

      }
        // close cursor and db
        cursor.close()
        db.close()
        return person
    }
    fun updatePerson(person: Person) {
        // get writable db
        val db = this.writableDatabase
        // create content value and put values from contact object
        val cv = ContentValues()
        cv.put(KeyName,person.name)
        cv.put(KeyImageFile,person.imageFile)
        cv.put(KeyPhone, person.phone)
        cv.put(KeyDepartment, person.department)
        cv.put(KeyStreet, person.street)
        cv.put(KeyCity, person.city)
        cv.put(KeyState, person.state)
        cv.put(KeyZip, person.zip)
        cv.put(KeyCountry, person.country)
        val id:Int = db.update(TableName,cv,"$KeyID=?",
            arrayOf(person.id.toString()))
        //close db
        db.close()
    }
}


