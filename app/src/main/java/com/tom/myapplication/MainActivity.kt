package com.tom.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val list_item = arrayListOf("項目1","項目2","項目3","項目4")
        val adapter = ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,list_item)
        val listView = findViewById<ListView>(R.id.listview)
        listView.adapter = adapter
        listView.setOnItemClickListener{
            parent,view,position,id ->
            Toast.makeText(this,"你選擇的是"+list_item[position],Toast.LENGTH_SHORT).show()

            val item = ArrayList<Item>()
            val array = resources.obtainTypedArray(R.array.resoureList)
            val gridview = findViewById<GridView>(R.id.gridview)
            val spinner = findViewById<Spinner>(R.id.spinner)
            for(i in 0 until array.length()){
                item.add(Item(array.getResourceId(i,0),"水果${i+1}"))}
            array.recycle()
            spinner.adapter = Myadapter(R.layout.adapter_horizontal,item)
            gridview.numColumns = 3
            gridview.adapter = Myadapter(R.layout.adapter_vertical,item)
            listView.adapter = ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,
                arrayListOf("項目1","項目2","項目3","項目4","項目5","項目6","項目7","項目8","項目9"))


        }
    }
}

data class Item(
    val photo:Int,
    val name:String
    )
class Myadapter constructor(private val layout:Int,private val data:ArrayList<Item>):BaseAdapter(){
    override fun getCount()=0
    override fun getItem(position:Int) = null
    override fun getItemId(position: Int) = 0L
    // 原理上讲 当ListView滑动的过程中 会有item被滑出屏幕 而不再被使用 这时候Android会回收这个条目的view 这个view也就是这里的convertView
    //当item1被移除屏幕的时候 我们会重新new一个View给新显示的item_new 而如果使用了这个convertView 我们其实可以复用它 这样就省去了new View的大量开销
        override fun getView(position:Int,converView:View?,parent:ViewGroup?):
                View{
            val view = View.inflate(parent?.context,layout,null)
            view.findViewById<ImageView>(R.id.img_photo).setImageResource(data[position].photo)
            view.findViewById<TextView>(R.id.tv_name).text= data[position].name
            return view
    }
}


