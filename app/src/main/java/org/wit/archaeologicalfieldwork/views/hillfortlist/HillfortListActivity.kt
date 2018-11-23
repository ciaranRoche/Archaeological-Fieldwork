package org.wit.archaeologicalfieldwork.views.hillfortlist

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import kotlinx.android.synthetic.main.fragment_hillfort_list.*
import org.wit.archaeologicalfieldwork.R
import org.wit.archaeologicalfieldwork.adapters.HillfortAdapter
import org.wit.archaeologicalfieldwork.adapters.HillfortListener
import org.wit.archaeologicalfieldwork.models.hillfort.HillfortJSONStore
import org.wit.archaeologicalfieldwork.models.hillfort.HillfortModel
import org.wit.archaeologicalfieldwork.models.hillfort.HillfortStore

class HillfortListActivity : AppCompatActivity(), HillfortListener {

    lateinit var hillforts: HillfortStore

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_hillfort_list)
        hillforts = HillfortJSONStore(applicationContext)

        toolbarList.title = title
        setSupportActionBar(toolbarList)

        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        loadHillforts()
    }

    override fun onHillfortClick(hillfort: HillfortModel) {
        // startActivityForResult(intentFor<HillFortProfileActivity>().putExtra("hillfort", hillfort), 0)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        loadHillforts()
        super.onActivityResult(requestCode, resultCode, data)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_list, menu)
        return super.onCreateOptionsMenu(menu)
    }

    private fun loadHillforts() {
        showHillforts(hillforts.findAll())
    }

    fun showHillforts(hillforts: List<HillfortModel>) {
        recyclerView.adapter = HillfortAdapter(hillforts, this)
        recyclerView.adapter?.notifyDataSetChanged()
    }
}