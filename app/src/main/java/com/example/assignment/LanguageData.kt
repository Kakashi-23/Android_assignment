package com.example.assignment

import com.google.gson.annotations.SerializedName


data class LanguageData(
        /*@SerializedName("total_count") var count:Int,
        @SerializedName("incomplete_results") var result:Boolean,*/
    @SerializedName("items") var items:ArrayList<Items>
) {
    data class Items(
            @SerializedName("id") var id:String,
            @SerializedName("full_name") var fullName:String,
            @SerializedName("owner") var owner:OwnerLogin,
            @SerializedName("description") var description:String,
    )
    data class OwnerLogin(
            @SerializedName("login") var login:String,
    )

}


