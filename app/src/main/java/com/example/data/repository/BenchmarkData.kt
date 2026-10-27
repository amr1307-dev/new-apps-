package com.example.data.repository

import com.example.data.model.CloserPitch
import com.example.data.model.CounterTactic
import com.example.data.model.ObjectionCase
import com.example.data.model.ScannedProductInfo

object BenchmarkData {

    val PRODUCTS: List<ScannedProductInfo> = listOf(
        // 1. Italian Artisan Leather Bag
        ScannedProductInfo(
            id = "scan_bag_01",
            nameAr = "حقيبة يد وكتف جلد عجل إيطالي محبب (طراز بيركين/بوتيغا)",
            categoryName = "حقائب جلدية فاخرة",
            officialBrandMatch = "Bottega / Hermès Style Full-Grain Calfskin Tote",
            officialSitePriceEur = 2450.0,
            onlineStorePriceEur = 850.0,
            suggestedRetailPriceEur = 400.0,
            artisanFeaturesAr = listOf(
                "جلد عجل إيطالي طبيعي 100% حبة كاملة (Full-Grain) مدبوغ نباتياً",
                "خياطة سرج يدوية مزدوجة متينة غير قابلة للتلف (Saddle-Stitching)",
                "إكسسوارات ومفاصل معدنية نحاسية أصلية مقاومة للخدش والصدأ",
                "بطانة جلد سويدي ناعمة مع جيوب حماية بسحاب داخلي",
                "شهادة جودة وحقيبة قماشية لحماية الجلد أثناء السفر"
            ),
            luxuryBrandComparisonAr = "تباع الحقيبة المطابقة في متاجر ميلانو وباريس بـ 2,450 يورو بسبب اسم العلامة وحملات التسويق. هنا تحصل على نفس جودة الجلد الطبيعي الصافي والخياطة اليدوية مباشرة من الورشة بسعر 400 يورو، بتوفير فوري قدره 2,050 يورو!",
            pitchesByLang = mapOf(
                "de" to CloserPitch(
                    foreignText = "Schauen Sie sich bitte dieses Leder an: Es ist 100% vollnarbiges italienisches Kalbsleder mit traditioneller Sattlernaht. Auf der offiziellen Marken-Website kostet das Original 2.450 Euro. Für nur 400 Euro bekommen Sie hier exakt die gleiche handwerkliche Lederqualität direkt aus der Werkstatt – eine Ersparnis von über 2.000 Euro, die Sie ein Leben lang begleiten wird.",
                    arabicTranslation = "تفضل بالنظر إلى هذا الجلد: إنه جلد عجل إيطالي أصلي 100% بحبة كاملة مع خياطة سرج تقليدية. في الموقع الرسمي للماركة سعرها 2,450 يورو. وبـ 400 يورو فقط تحصل هنا على نفس الجودة الحرفية العالية مباشرة من الورشة، بتوفير يتجاوز 2,000 يورو مع قطعة تدوم معك مدى الحياة.",
                    arabicPhonetics = "شاون زي زيش بيته ديزِس ليدر آن: إس إست هوندرت بروتسينت فول-ناربيغِس إيتاليينيشِس كالبس-ليدر ميت تراديتسيونيلر زاتلر-ناهت. أوف دير أوفيتسييلن ماركن-فيبسايت كوستت داز أوريجينال تسفاي-تاوزند فير-هوندرت فونفتسيش أويغو. فور نور فير-هوندرت أويغو بيكومن زي هير إكزاكت دي غلايخه هاندفيركليشه ليدر-كفاليتيت.",
                    valueProofStatement = "توفير 2,050 يورو مقارنة بالموقع الرسمي (وفرت 83% من السعر)"
                ),
                "en" to CloserPitch(
                    foreignText = "Feel this leather: it is 100% full-grain Italian calfskin with handcrafted double stitching. On the official boutique website, the branded original is 2,450 Euros. At just 400 Euros today, you are receiving the identical luxury leather grade and artisan hardware directly from our workshop—saving over 2,000 Euros on a piece made to last a lifetime.",
                    arabicTranslation = "المس هذا الجلد: إنه جلد عجل إيطالي أصلي 100% حبة كاملة بخياطة يدوية مزدوجة. في بوتيك الماركة الرسمي سعرها 2,450 يورو. وبـ 400 يورو فقط اليوم تحصل على نفس درجة الجلد الفاخر مباشرة من ورشتنا، بتوفير أكثر من 2,000 يورو لقطعة تدوم العمر كله.",
                    arabicPhonetics = "فيل ذيس ليذر: إت إز ون هاندرد بيرسنت فول-جرين إيتاليان كاف-سكين وذ هاند-كرافتد دبل ستيتشنغ. أون ذا أوفيشال بوتيك ويبسايت إت إز تو ثاوزند فور هاندرد فيفتي يوروز.",
                    valueProofStatement = "Save €2,050 vs official boutique (83% direct workshop saving)"
                ),
                "it" to CloserPitch(
                    foreignText = "Guardi la grana di questa pelle: è 100% vera pelle di vitello pieno fiore conciata al vegetale. Nelle boutique ufficiali costa oltre 2.400 Euro. A soli 400 Euro qui prende la medesima maestria artigianale toscana senza ricarichi di marchio.",
                    arabicTranslation = "انظر إلى ملمس ومسام هذا الجلد: جلد عجل أصلي 100% حبة كاملة مدبوغ نباتياً. في البوتيكات الرسمية سعره يتجاوز 2,400 يورو. وبـ 400 يورو فقط هنا تأخذ نفس الحرفية الإيطالية المتقنة دون رسوم تسويق الماركة.",
                    arabicPhonetics = "غواردي لا غرانا دي كويستا بيلّي: إيه تشينتو بير تشينتو فيرا بيلّي دي فيتيلّو بينو فيوري كونشياتا أل فيجيتالي.",
                    valueProofStatement = "Risparmio di oltre 2.000€ rispetto al listino ufficiale"
                )
            )
        ),

        // 2. Genuine Lambskin Biker Jacket
        ScannedProductInfo(
            id = "scan_jacket_02",
            nameAr = "جاكيت جلد غنم طبيعي فاخر (طراز توم فورد / سان لوران)",
            categoryName = "الملابس الجلدية الراقية",
            officialBrandMatch = "Tom Ford / Saint Laurent Lambskin Biker Edition",
            officialSitePriceEur = 2100.0,
            onlineStorePriceEur = 690.0,
            suggestedRetailPriceEur = 400.0,
            artisanFeaturesAr = listOf(
                "جلد غنم طبيعي فائق النعومة وخفيف الوزن (Nappa Lambskin)",
                "سحابات معدنية يابانية أصلية YKK مقاومة للضغط والانزلاق",
                "بطانة داخلية تنفسية مقاومة للعرق والرياح",
                "تفصيل مجسم ومريح للجسم يتمدد طبيعياً ليناسب قوامك"
            ),
            luxuryBrandComparisonAr = "الجاكيت الأصلي بالماركة يباع بـ 2,100 يورو في المتاجر الأوروبية، وبـ 690 يورو في المتاجر الإلكترونية للنسخ الراقية. هنا بسعر 400 يورو تحصل على جلد طبيعي مدبوغ يدوياً بدون أي إضافات كيميائية.",
            pitchesByLang = mapOf(
                "de" to CloserPitch(
                    foreignText = "Probieren Sie diese Jacke nur für 10 Sekunden an: Sie wiegt fast nichts und passt sich perfekt Ihrem Körper an. Im offiziellen Markenstore zahlen Sie 2.100 Euro hauptsächlich für das Logo. Hier bei 400 Euro zahlen Sie rein für erstklassiges Lammleder und deutsche Passform.",
                    arabicTranslation = "جرب ارتداء هذا الجاكيت لـ 10 ثوانٍ فقط: خفيف الوزن جداً ويتكيف تماماً مع قوامك. في متجر العلامة الرسمي تدفع 2,100 يورو في المقام الأول من أجل الشعار. هنا بـ 400 يورو تدفع ثمن جلد الغنم الطبيعي الفاخر والتفصيل المريح فقط.",
                    arabicPhonetics = "بروبيرن زي ديزه ياكه نور فور تسين سِكوندن آن: زي فيغت فاست نيشتس أوند باست زيش بيرفكت إيرم كوربر آن. إم أوفيتسييلن ماركن-شتور تسالن زي تسفاي-تاوزند آين-هوندرت أويغو.",
                    valueProofStatement = "وفرت 1,700 يورو مقابل متجر الماركة الرسمي"
                )
            )
        ),

        // 3. Royal Aged Oud & Amber Oil
        ScannedProductInfo(
            id = "scan_oud_03",
            nameAr = "عطر وزيت العود الملكي والعنبر الصافي (فئة أمواج / روجا دوف)",
            categoryName = "الزيوت العطرية النقية",
            officialBrandMatch = "Amouage / Roja Dove Pure Aged Royal Oud",
            officialSitePriceEur = 850.0,
            onlineStorePriceEur = 320.0,
            suggestedRetailPriceEur = 200.0,
            artisanFeaturesAr = listOf(
                "زيت عطري خام نقي 100% غير مخفف بالكحول أو الماء",
                "ثبات هائل يدوم لأكثر من 48 ساعة على الملابس والبشرة",
                "قارورة كريستالية فاخرة بحجم 50 مل معتمدة لحقائب السفر بالطائرة"
            ),
            luxuryBrandComparisonAr = "تباع زجاجة العود المماثلة في باريس ولندن بـ 850 يورو بعد تخفيفها بالكحول، بينما هذا زيت خام نقي يدوم لسنة كاملة بسعر 200 يورو فقط.",
            pitchesByLang = mapOf(
                "de" to CloserPitch(
                    foreignText = "Dies ist reine Essenz ohne einen einzigen Tropfen Alkohol. Während Markenparfüms für 850 Euro zu 80% aus Alkohol bestehen, hält dieser pure Tropfen 48 Stunden. Bei 200 Euro reicht Ihnen dieser Flakon für über ein ganzes Jahr.",
                    arabicTranslation = "هذه خلاصة عطرية نقية بدون قطرة كحول واحدة. بينما تتكون عطور الماركات التي تباع بـ 850 يورو من 80% كحول، تدوم قطرة واحدة من هذا الزيت 48 ساعة. وبـ 200 يورو تكفيك هذه الزجاجة لأكثر من سنة كاملة.",
                    arabicPhonetics = "ديز إست راينه إيسينتس أونه آينن آينتسيغن تروبفن ألكوهول. فيرند ماركن-بارفيومز فور أخت-هوندرت فونفتسيش أويغو تسو أختسيش بروتسينت أوس ألكوهول بشتيهن.",
                    valueProofStatement = "زيت خام نقي 100% بدون كحول - يوفر 650 يورو"
                )
            )
        )
    )

    // Objection Handling Database
    val OBJECTIONS: List<ObjectionCase> = listOf(
        ObjectionCase(
            id = "obj_too_expensive_de",
            touristSpokenForeign = "Das ist mir zu teuer / 400 Euro ist über meinem Budget.",
            touristSpokenArabic = "السعر مرتفع جداً بالنسبة لي / 400 يورو تتجاوز ميزانيتي.",
            languageCode = "de",
            detectedObjectionType = "price_high",
            tactics = listOf(
                CounterTactic(
                    id = "tac_val_de_1",
                    titleAr = "التكتيك 1: المقارنة بجلد الماركات العالمية (Value Anchor)",
                    strategyExplanationAr = "أثبت للعميل أن 400 يورو لجلد حبّة كاملة تدوم 10 سنوات أوفر بكثير من شراء حقيبة بـ 150 يورو من جلد صناعي تتشقق بعد 6 أشهر.",
                    foreignSpeech = "Ich verstehe Sie vollkommen. Aber bitte bedenken Sie: Billige Taschen für 150 Euro nutzen Spaltleder, das nach 6 Monaten brüchig wird. Hier zahlen Sie 400 Euro für echtes vollnarbiges Leder, das mit den Jahren noch schöner wird. Pro Jahr kostet Sie das keine 40 Euro für ein lebenslanges Meisterstück.",
                    arabicPhonetics = "إيش فيرشتيهه زي فول-كومن. آبر بيته بيدنكن زي: بيليغه تاشن فور هوندرت-فونفتسيش أويغو نوتسن شبيلت-ليدر. هير تسالن زي فير-هوندرت أويغو فور إيشتِس فول-ناربيغِس ليدر. برو يار كوستت زي داز كاينه فيرتسيش أويغو.",
                    arabicTranslation = "أتفهمك تماماً. ولكن فكر في هذا: الحقائب الرخيصة بـ 150 يورو تستخدم جلداً صناعياً يتشقق بعد 6 أشهر. هنا تدفع 400 يورو لجلد أصلي يزداد جمالاً مع السنين. على مدار سنوات استخدامها لن تكلفك حتى 40 يورو سنوياً مقابل تحفة تدوم العمر كله."
                ),
                CounterTactic(
                    id = "tac_bundle_de_2",
                    titleAr = "التكتيك 2: حزمة الهدية المجانية الفاخرة (Sweeten Deal)",
                    strategyExplanationAr = "لا تنزل السعر مباشرة، بل أضف هدية جلدية مجانية (محفظة أو حزام) وشهادة الضمان لإشعاره بالربح الفوري.",
                    foreignSpeech = "Ich möchte, dass Sie mit einem Lächeln nach Hause fliegen. Wenn wir die 400 Euro jetzt besiegeln, lege ich Ihnen eine handgefertigte Ledergeldbörse im Wert von 60 Euro gratis dazu, plus ein Zertifikat für den zollfreien Flug.",
                    arabicPhonetics = "إيش موشته، داس زي ميت آينم ليشلن ناخ هاوزه فليغن. فين فير دي فير-هوندرت أويغو ييتست بيزيغلن، ليغه إيش إينن آينه هاند-غِفرتيكته ليدر-غلد-بورزه إم فيرت فون زيكستسيش أويغو غراتيس داتسو.",
                    arabicTranslation = "يهمني أن تعود لوطنك مسروراً وراضياً تماماً. إذا اتفقنا الآن على 400 يورو، سأهديك محفظة جلدية يدوية بقيمة 60 يورو مجاناً، بالإضافة إلى شهادة الفحص المعفى من الجمارك في المطار."
                ),
                CounterTactic(
                    id = "tac_split_de_3",
                    titleAr = "التكتيك 3: تسهيل خيار العملة والدفع (Flexibility)",
                    strategyExplanationAr = "اعرض عليه الدفع بالبطاقة أو اليورو أو عملته المفضلة لتبديد حاجز الميزانية النقدية.",
                    foreignSpeech = "Sie können ganz bequem mit Visa oder Mastercard in Euro abrechnen, sodass Sie kein Bargeld wechseln müssen. Soll ich Ihnen die Quittung gleich fertig machen?",
                    arabicPhonetics = "زي كونن غانتس بيكفيم ميت فيزا أودر ماستركارد إن أويغو آب-ريشنن. زول إيش إينن دي كفيتونغ غلايش فيرتيش ماخن؟",
                    arabicTranslation = "يمكنك الدفع بكل راحة ببطاقة فيزا أو ماستركارد باليورو دون الحاجة لصرف كاش. هل أجهز لك فاتورة الضمان فوراً؟"
                )
            )
        ),
        ObjectionCase(
            id = "obj_too_expensive_en",
            touristSpokenForeign = "It is too expensive, 400 Euros is above my budget.",
            touristSpokenArabic = "السعر غالي جداً، 400 يورو أعلى من ميزانيتي.",
            languageCode = "en",
            detectedObjectionType = "price_high",
            tactics = listOf(
                CounterTactic(
                    id = "tac_val_en_1",
                    titleAr = "التكتيك 1: إبراز توفير 2,000 يورو مقابل الماركات",
                    strategyExplanationAr = "وضّح للعميل القيمة الهائلة مقارنة بسعر الموقع الرسمي للعلامة الفاخرة.",
                    foreignSpeech = "I completely respect that. But remember, the branded version in London or New York is 2,400 Euros. At 400 Euros, you are paying strictly for genuine full-grain leather directly from our craftsmen, saving 2,000 Euros on a piece that will last 15 years.",
                    arabicPhonetics = "آي كومبليتلي ريسبكت ذات. بت ريممبر، ذا براندد فيرجن إز تو ثاوزند فور هاندرد يوروز.",
                    arabicTranslation = "أحترم رأيك تماماً. لكن تذكر أن النسخة الأصلية للماركة في لندن أو نيويورك تباع بـ 2,400 يورو. وبـ 400 يورو أنت تدفع مباشرة للحرفيين لجلد طبيعي أصلي ويدوم 15 سنة."
                )
            )
        )
    )
}
