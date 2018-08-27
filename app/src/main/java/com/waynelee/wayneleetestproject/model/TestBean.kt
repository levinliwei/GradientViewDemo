package com.waynelee.wayneleetestproject.model

/**
 * @author      levin
 * @Summary     TestBean
 * @email       levin.li@teamar.cn
 * @data        2018/8/23
 * @org         Aurora Team
 */
class TestBean(name: String, id: Int) {

    var name: String? = null
    var id: Int = 0

    init {
        this.name = name
        this.id = id
    }
}