package com.example.parstagramapp

import com.parse.ParseClassName
import com.parse.ParseFile
import com.parse.ParseObject
import com.parse.ParseUser

// every post class has:
// Description : String
// Image : File
// User : User
// get and set for all

@ParseClassName("Post") // associated with "Post" table in back4app database

class Post : ParseObject() {

    fun getDescription(): String? {
        // get description via keys (name of each column in back4app)
        return getString(KEY_DESCRIPTION) // getString is a special method assoc w/ parse object
    }

    fun setDescription(description : String) {
        put(KEY_DESCRIPTION, description)
    }

    fun getImage(): ParseFile? {
        return getParseFile(KEY_IMAGE)
    }

    fun setImage(parsefile: ParseFile) {
        put(KEY_IMAGE, parsefile)
    }

    fun getUser(): ParseUser? {
        return getParseUser(KEY_USER)
    }

    fun setUser(user: ParseUser) {
        put(KEY_USER, user)
    }



    companion object {
        const val KEY_DESCRIPTION = "description"
        const val KEY_IMAGE = "image"
        const val KEY_USER = "user"
    }

}