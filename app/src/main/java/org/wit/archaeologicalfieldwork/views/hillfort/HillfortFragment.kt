package org.wit.archaeologicalfieldwork.views.hillfort

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import org.jetbrains.anko.AnkoLogger
import org.wit.archaeologicalfieldwork.R
import org.wit.archaeologicalfieldwork.models.hillfort.HillfortModel

class HillfortFragment : Fragment(), AnkoLogger {

    lateinit var presenter: HillfortPresenter

    var hillfort = HillfortModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        presenter = HillfortPresenter(this)

        hillfort = arguments!!.getParcelable("hillfort") as HillfortModel

        val view = inflater.inflate(R.layout.activity_hillfort, container, false)
        return view
    }

    companion object {
        fun newInstance(hillfort: HillfortModel): HillfortFragment {
            val args = Bundle()
            args.putParcelable("hillfort", hillfort)
            val fragment = HillfortFragment()
            fragment.arguments = args
            return fragment
        }
    }
//    override fun onCreate(savedInstanceState: Bundle?) {
//        setTheme(R.style.AppTheme)
//        super.onCreate(savedInstanceState)
//
//        if (!userLogged) {
//            startActivityForResult<StartUpView>(0)
//        }
//
//        val contentView = layoutInflater.inflate(R.layout.activity_hillfort, null, false)
//
//        app = application as MainApp
//
//        if (intent.hasExtra("hillfort_edit")) {
//            btnAdd.setText(R.string.save_hillfort)
//            hillfort = intent.extras.getParcelable<HillfortModel>("hillfort_edit")
//            hillfortName.setText(hillfort.name)
//            description.setText(hillfort.description)
//            location = hillfort.location
//            if (loggeduser.stats.isNotEmpty()) {
//                val found = loggeduser.stats.find { h -> h.hillfort == hillfort.id }
//                if (found != null) {
//                    visitedBox.isChecked = true
//                    visitedBox.text = String.format("Visited on ${found.date}")
//                }
//            }
//        } else {
//            btnDelete.visibility = View.INVISIBLE
//        }
//
//        btnDelete.setOnClickListener {
//            app.hillforts.delete(hillfort.copy())
//            setResult(AppCompatActivity.RESULT_OK)
//            toast("Hillfort Removed")
//            startActivityForResult<HillfortListFragment>(0)
//        }
//
//        btnAdd.setOnClickListener {
//            hillfort.name = hillfortName.text.toString()
//            hillfort.description = description.text.toString()
//            hillfort.location = location
//            if (hillfort.name.isNotEmpty()) {
//                if (intent.hasExtra("hillfort_edit")) {
//                    app.hillforts.update(hillfort.copy())
//                } else {
//                    app.hillforts.create(hillfort.copy())
//                }
//                setResult(AppCompatActivity.RESULT_OK)
//                finish()
//            } else {
//                toast("Please Enter a title")
//            }
//        }
//
//        hillfortLocation.setOnClickListener {
//            startActivityForResult(intentFor<MapsActivity>().putExtra("location", location), LOCATION_REQUEST)
//        }
//
// //        chooseImage.setOnClickListener {
// //            showImagePicker(this, IMAGE_REQUEST)
// //        }
//
//        visitedBox.setOnClickListener {
//            toast("Hillfort Visited")
//            val foundStats = app.users.getStats(loggeduser)
//            val foundHillfort = foundStats!!.find { s -> s.hillfort == hillfort.id }
//            if (foundHillfort == null) {
//                stat.hillfort = hillfort.id
//                stat.date = getDate()
//                loggeduser.stats.add(stat)
//            } else {
//                stat.date = getDate()
//                loggeduser.stats[loggeduser.stats.indexOf(stat)] = stat
//            }
//            app.users.update(loggeduser)
//        }
//    }
//
//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//        when (requestCode) {
//            IMAGE_REQUEST -> {
//                if (data != null) {
//                    val image: String = data.data.toString()
//                    hillfort.images += image
//                    toast("Image Uploaded")
//                }
//            }
//            LOCATION_REQUEST -> {
//                if (data != null) {
//                    location = data.extras.getParcelable<Location>("location")
//                }
//            }
//        }
//    }
}