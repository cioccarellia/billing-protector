package com.andreacioccarelli.billingprotector.data

/**
 * Designed and Developed by Andrea Cioccarelli
 */

abstract class AppDefinition{
    abstract val field: String
    abstract val hash: String
    abstract val criteria: SelectionCriteria
    abstract val name: String
}