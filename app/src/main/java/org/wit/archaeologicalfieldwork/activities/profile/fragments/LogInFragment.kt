package org.wit.archaeologicalfieldwork.activities.profile.fragments

import android.os.Bundle
import android.support.design.widget.TextInputEditText
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import org.wit.archaeologicalfieldwork.R
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.support.v4.intentFor
import org.jetbrains.anko.support.v4.toast
import org.mindrot.jbcrypt.BCrypt
import org.wit.archaeologicalfieldwork.activities.profile.ProfileActivity
import org.wit.archaeologicalfieldwork.activities.profile.loggeduser
import org.wit.archaeologicalfieldwork.activities.profile.userLogged
import org.wit.archaeologicalfieldwork.models.UserJSONStore
import org.wit.archaeologicalfieldwork.models.UserStore


class LogInFragment : Fragment(), AnkoLogger{

  lateinit var users: UserStore

  override fun onCreate(savedInstanceState: Bundle?){
    super.onCreate(savedInstanceState)
    users = UserJSONStore(this.context!!)
  }

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    val view = inflater.inflate(R.layout.login_fragment,container,false)
    val email: TextInputEditText? = view.findViewById(R.id.fragment_userEmailLogin)
    val password: TextInputEditText? = view.findViewById(R.id.fragment_userPasswordLogin)
    val login: Button? = view.findViewById(R.id.fragment_login)

    login?.setOnClickListener {
      val checkUser = users.checkUser(email?.text.toString())
      if(checkUser){
        val getUser = users.getUser(email?.text.toString())
        if(BCrypt.checkpw(password?.text.toString(), getUser.password)){
          userLogged = true
          loggeduser = getUser
          startActivityForResult(intentFor<ProfileActivity>().putExtra("logged_in", loggeduser), 0)
        }
      }
      else{
        toast("Incorrect Details")
      }
    }

    return view
  }
}


