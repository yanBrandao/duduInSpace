package br.com.woodriver.game

import com.badlogic.gdx.Preferences

class MaterialManager(private val preferences: Preferences) {
    private val MATERIAL_KEY = "special_materials"
    private val DROP_CHANCE = 0.1f // 10% chance to drop material

    fun getMaterialCount(): Int {
        return preferences.getInteger(MATERIAL_KEY, 0)
    }

    fun addMaterial(amount: Int = 1) {
        val current = getMaterialCount()
        preferences.putInteger(MATERIAL_KEY, current + amount)
        preferences.flush()
    }

    fun spendMaterial(amount: Int): Boolean {
        val current = getMaterialCount()
        if (current >= amount) {
            preferences.putInteger(MATERIAL_KEY, current - amount)
            preferences.flush()
            return true
        }
        return false
    }

    fun shouldDropMaterial(): Boolean {
        return Math.random() < DROP_CHANCE
    }
} 