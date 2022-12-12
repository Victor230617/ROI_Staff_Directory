package com.example.roi_staff_directry
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
//Class MyAdaptor
class CustomAdapter(private  val context: Context,
                    private val arrayList: ArrayList<Person>) : BaseAdapter(){
    private lateinit var txtName: TextView
    private lateinit var txtPhone:  TextView
    private lateinit var txtDepartment: TextView
    private lateinit var txtStreet: TextView
    private lateinit var txtCity: TextView
    private  lateinit var txtState:  TextView
    private  lateinit var txtZip: TextView
    private  lateinit var txtCountry: TextView
    private  lateinit var ivImage: ImageView
    override fun getCount(): Int {
        return arrayList.size
    }
    override fun getItem(position: Int): Int {
        return position
    }
    override fun getItemId(position: Int): Long {
        return position.toLong()
    }
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var convertView = convertView
        convertView = LayoutInflater.from(context).inflate(R.layout.row_item, parent, false)
        txtName = convertView.findViewById(R.id.name)
        txtPhone = convertView.findViewById(R.id.phone)
        txtDepartment = convertView.findViewById(R.id.department)
        txtStreet = convertView.findViewById(R.id.street)
        txtCity = convertView.findViewById(R.id.city)
        txtState = convertView.findViewById(R.id.state)
        txtZip = convertView.findViewById(R.id.zip)
        txtCountry = convertView.findViewById(R.id.country)
        ivImage = convertView.findViewById(R.id.imageFile)

        txtName.text = arrayList[position].name
        txtPhone.text = arrayList[position].phone
        txtDepartment.text = arrayList[position].department
        txtStreet.text = arrayList[position].street
        txtCity.text = arrayList[position].city
        txtState.text = arrayList[position].state
        txtZip.text = arrayList[position].zip
        txtCountry.text = arrayList[position].country
        ivImage.setImageResource(context.resources.getIdentifier(
            arrayList[position].imageFile, "drawable",
            "com.example.roi_staff_directry"));
        return convertView
    }

}