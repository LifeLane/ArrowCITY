with open("app/src/main/java/com/example/data/AppDatabase.kt", "r") as f:
    content = f.read()

companion_code = """    abstract fun levelProgressDao(): LevelProgressDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: android.content.Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = androidx.room.Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "arrow_city_database"
                )
                .fallbackToDestructiveMigration()
                .build()
                INSTANCE = instance
                instance
            }
        }
    }
}"""

content = content.replace("    abstract fun levelProgressDao(): LevelProgressDao\n}", companion_code)

with open("app/src/main/java/com/example/data/AppDatabase.kt", "w") as f:
    f.write(content)
