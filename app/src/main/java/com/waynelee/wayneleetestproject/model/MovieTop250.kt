package com.waynelee.wayneleetestproject.model

/**
 * @author      levin
 * @Summary     Movie
 * @email       levin.li@teamar.cn
 * @data        2018/7/25
 * @org         Aurora Team
 */
class MovieTop250 {

     val count: Int = 0
     val start: Int = 0
     val total: Int = 0
     val title: String? = null
     val subjects: List<SubjectsBean>? = null

    class SubjectsBean {

         val rating: RatingBean? = null
         val title: String? = null
         val collect_count: Int = 0
         val original_title: String? = null
         val subtype: String? = null
         val year: String? = null
         val images: ImagesBean? = null
         val alt: String? = null
         val id: String? = null
         val genres: List<String>? = null
         val casts: List<UserBean>? = null
         val directors: List<UserBean>? = null
    }

    class RatingBean {
         val max: Int = 0
         val average: Double = 0.toDouble()
         val stars: String? = null
         val min: Int = 0
    }

    class ImagesBean {
         val small: String? = null
         val large: String? = null
         val medium: String? = null
    }

    class UserBean {
         val alt: String? = null
         val avatars: ImagesBean? = null
         val name: String? = null
         val id: String? = null
    }
}