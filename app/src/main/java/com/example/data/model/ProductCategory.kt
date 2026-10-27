package com.example.data.model

enum class ProductCategory(
    val id: String,
    val nameAr: String,
    val iconEmoji: String,
    val descriptionAr: String
) {
    LEATHER("leather", "الجلود الطبيعية", "🧥", "جاكيتات، حقائب، محافظ وأحزمة يد"),
    PERFUMES("perfumes", "العطور والزيوت", "✨", "العود الملكي، العنبر، زهرة اللوتس، والمسك"),
    EYEWEAR("eyewear", "النظارات الشمسية", "🕶️", "عدسات بولارايزد وحماية UV400 وإطارات خفيفة"),
    CRAFTS("crafts", "التحف والهدايا", "🏺", "شالات كشمير، فضيات يدوية، برديات ونحاس")
}
