package com.gaming.android.tearsdatabase

class DataSource {

    companion object {
        fun loadWeapons(): List<Item> {
            return listOf(
                Item(R.string.royal_guard_claymore, R.drawable.royal_guard_claymore),
                Item(R.string.boulder_breaker, R.drawable.boulder_breaker),
                Item(R.string.fierce_deity_sword, R.drawable.fierce_deity_sword),
                Item(R.string.royal_claymore, R.drawable.royal_claymore),
                Item(R.string.biggorns_sword, R.drawable.biggorons_sword),
                Item(R.string.gloom_club, R.drawable.gloom_club),
                Item(R.string.royal_guards_sword, R.drawable.royal_guards_sword),
                Item(R.string.gloom_sword, R.drawable.gloom_sword),
                Item(R.string.gloom_spear, R.drawable.gloom_spear),
                Item(R.string.knights_claymore, R.drawable.knights_claymore),
                Item(R.string.royal_broadsword, R.drawable.royal_broadsword),
                Item(R.string.scimitar_of_the_seven, R.drawable.scimitar_of_the_seven),
                Item(R.string.eightfold_longblade, R.drawable.eightfold_longblade),
            )
        }
    }
}