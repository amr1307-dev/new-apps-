package com.example.data.repository

import com.example.data.model.PhraseItem
import com.example.data.model.PriceObjection
import com.example.data.model.Product
import com.example.data.model.ProductCategory
import com.example.data.model.ProductQuestion

object DefaultCatalog {

    val PRODUCTS: List<Product> = listOf(
        // 1. Genuine Leather Jacket
        Product(
            id = "leather_jacket_01",
            nameAr = "جاكيت جلد طبيعي (خروف مدبوغ نباتي)",
            nameNativeMap = mapOf(
                "de" to "Echtleder-Jacke (Lammleder)",
                "it" to "Giacca in Vera Pelle di Agnello",
                "ru" to "Куртка из натуральной кожи ягненка",
                "en" to "Genuine Lambskin Leather Jacket",
                "fr" to "Veste en Cuir Véritable d'Agneau",
                "es" to "Chaqueta de Cuero Auténtico de Cordero"
            ),
            category = ProductCategory.LEATHER,
            iconEmoji = "🧥",
            priceLocal = 4800.0,
            priceEur = 95.0,
            priceUsd = 105.0,
            verifiedFacts = listOf(
                "جلد غنم طبيعي 100% ناعم ومرن للغاية",
                "دباغة نباتية بدون كيماويات ضارة - غير قابلة للتشقق",
                "بطانة حريرية تنفسية عازلة للبرودة والرطوبة",
                "ضمان استبدال وإصلاح لمدة سنتين ضد عيوب الصناعة"
            ),
            pitch = mapOf(
                "de" to PhraseItem(
                    foreignText = "Diese Jacke ist aus 100% echtem Lammleder handgefertigt, extrem weich, atmungsaktiv und hält ein Leben lang.",
                    arabicTranslation = "هذا الجاكيت مصنوع يدويًا من جلد خروف طبيعي 100%، ناعم جدًا ومريح، ويدوم معك مدى الحياة.",
                    arabicPhonetics = "ديزه ياكه إست أوس هوندرت بروتسينت إيشتيم لام-ليدر هاند-جِفرتيكت، إكستريم فايش، أتمونغس-أكتيف، أوند هيلت آين ليبن لانغ.",
                    breakdownWords = listOf(
                        "Diese Jacke" to "ديزه ياكه (هذا الجاكيت)",
                        "aus echtem Lammleder" to "أوس إيشتيم لام-ليدر (من جلد غنم طبيعي)",
                        "handgefertigt" to "هاند-جِفرتيكت (مصنوع يدويًا)",
                        "extrem weich" to "إكستريم فايش (فائق النعومة)",
                        "hält ein Leben lang" to "هيلت آين ليبن لانغ (يدوم مدى الحياة)"
                    )
                ),
                "it" to PhraseItem(
                    foreignText = "Questa giacca è realizzata a mano in 100% vera pelle di agnello, morbidissima e duratura nel tempo.",
                    arabicTranslation = "هذا الجاكيت مصنوع يدويًا من جلد حمل أصلي 100%، شديد النعومة ومصمم ليدوم طويلاً.",
                    arabicPhonetics = "كويستا جاكا إيه ريالاتزاتا آ مانو إن تشينتو بير تشينتو فيرا بيلّي دي أنييّلو، موربيديسيما إيه دوراتورا نيل تيمبو."
                ),
                "ru" to PhraseItem(
                    foreignText = "Эта куртка сделана вручную из 100% натуральной кожи ягненка, очень мягкая и прослужит долгие годы.",
                    arabicTranslation = "هذا الجاكيت مصنوع يدوياً من جلد خروف طبيعي 100%، ناعم جداً ويخدم لسنوات طويلة.",
                    arabicPhonetics = "إيتا كورتكا سديَلانا فرورشنويو إز ستو براتسينتوف ناتورالناي كوجي يانيونكا، أوتشين مياخكايا إي بروسلوجيت دولغيي غودي."
                ),
                "en" to PhraseItem(
                    foreignText = "This jacket is handcrafted from 100% genuine lambskin leather, incredibly soft, breathable, and made to last a lifetime.",
                    arabicTranslation = "هذا الجاكيت مصنوع يدويًا من جلد حمل طبيعي 100%، فائق النعومة ومريح ومصنوع ليدوم مدى الحياة.",
                    arabicPhonetics = "ذيس جاكيت إز هاند-كرافتد فروم ون هاندرد بيرسنت جينوين لامب-سكين ليذر."
                ),
                "fr" to PhraseItem(
                    foreignText = "Cette veste est fabriquée à la main en cuir d'agneau 100% véritable, très souple et d'une durabilité exceptionnelle.",
                    arabicTranslation = "هذه السترة مصنوعة يدويًا من جلد خروف حقيقي 100%، مرنة للغاية وذات متانة استثنائية.",
                    arabicPhonetics = "سيت فيست إيه فابريكيه آ لا مان أون كوير دانيو صان بورصان فيريتابل."
                ),
                "es" to PhraseItem(
                    foreignText = "Esta chaqueta está hecha a mano con 100% cuero auténtico de cordero, sumamente suave y resistente.",
                    arabicTranslation = "هذه السترة مصنوعة يدوياً من جلد الخروف الأصلي 100%، ناعمة للغاية ومقاومة.",
                    arabicPhonetics = "إستا تشاكيتا إستا إيتشا آ مانو كون ثيين بور ثيينتو كويرو أوتينتيكو دي كورديرو."
                )
            ),
            questions = listOf(
                ProductQuestion(
                    id = "q_leather_genuine",
                    questionAr = "هل هذا جلد طبيعي حقيقي؟ وكيف أثبت ذلك للعميل؟",
                    questionForeign = mapOf(
                        "de" to "Ist das wirklich echtes Leder?",
                        "it" to "È veramente vera pelle naturale?",
                        "ru" to "Это действительно натуральная кожа?",
                        "en" to "Is this really genuine natural leather?",
                        "fr" to "Est-ce vraiment du cuir naturel?",
                        "es" to "¿Es realmente cuero natural legítimo?"
                    ),
                    answers = mapOf(
                        "de" to PhraseItem(
                            foreignText = "Ja, absolut. Riechen Sie daran und spüren Sie die Poren. Wir geben Ihnen eine zweijährige Garantie mit Zertifikat.",
                            arabicTranslation = "نعم بكل تأكيد. يمكنك شم الرائحة ولمس مسام الجلد الطبيعية، ونمنحك شهادة ضمان لمدة عامين.",
                            arabicPhonetics = "يا، أبسولوت. ريشن زي داران أوند شبورن زي دي بورن. فير غيبن إينن آينه تسفاي-يارِيغه غارانتيه ميت تسيرتيفيكات."
                        ),
                        "en" to PhraseItem(
                            foreignText = "Yes, absolutely! Feel the natural texture and grain. We provide a two-year certificate of authenticity with it.",
                            arabicTranslation = "نعم بكل تأكيد! المس ملمس الجلد ومسامه الطبيعية، ونقدم لك شهادة أصالة وضمان لمدة عامين.",
                            arabicPhonetics = "يَس، أبسولوتلي! فيل ذا ناتشرال تيكستشر أند جرين."
                        ),
                        "ru" to PhraseItem(
                            foreignText = "Да, конечно. Обратите внимание на текстуру и запах. Мы даем 2 года гарантии с сертификатом.",
                            arabicTranslation = "نعم بالتأكيد. لاحظ الملمس والرائحة، نمنح ضمان سنتين مع شهادة معتمدة.",
                            arabicPhonetics = "دا، كونيشنو. أوبراتيتي فنيَمانيه نا تيكستورو إي زاباخ."
                        )
                    )
                ),
                ProductQuestion(
                    id = "q_leather_flight",
                    questionAr = "هل يسهل طيه وحمله في حقيبة الطائرة دون تجعد دائم؟",
                    questionForeign = mapOf(
                        "de" to "Kann ich die Jacke problemlos im Koffer transportieren?",
                        "en" to "Can I pack this jacket safely in my travel luggage?",
                        "ru" to "Можно ли сложить куртку в чемодан без заломов?"
                    ),
                    answers = mapOf(
                        "de" to PhraseItem(
                            foreignText = "Ja, das Leder ist sehr geschmeidig. Einfach locker rollen, es entstehen keine dauerhaften Falten.",
                            arabicTranslation = "نعم، الجلد مرن جداً. فقط لفه برفق ولن تظهر عليه أي تجاعيد دائمة.",
                            arabicPhonetics = "يا، داز ليدر إست زير غِشمايديش. آينفاخ لوكر رولن، إس إنتشتيهن كاينه داورهافتن فالتن."
                        ),
                        "en" to PhraseItem(
                            foreignText = "Yes, lambskin is very flexible. Just roll it gently in your luggage and it hangs back smoothly.",
                            arabicTranslation = "نعم، جلد الغنم مرن للغاية. فقط لفه برفق في الحقيبة وسيعود لشكله الانسيابي بمجرد تعليقه.",
                            arabicPhonetics = "يَس، لامب-سكين إز فيري فليكسيبل. جاست رول إت جينتلي."
                        )
                    )
                )
            ),
            objections = listOf(
                ProductQuestion(
                    id = "obj_leather_expensive",
                    questionAr = "الاعتراض: السعر غالي مقارنة بالمحلات الأخرى",
                    questionForeign = mapOf(
                        "de" to "Das ist zu teuer für mich / woanders ist es billiger.",
                        "en" to "That's too expensive / I saw it cheaper elsewhere.",
                        "ru" to "Это слишком дорого / в другом месте дешевле."
                    ),
                    answers = mapOf(
                        "de" to PhraseItem(
                            foreignText = "Ich verstehe Sie. Billigere Jacken nutzen Spaltleder mit Plastikbeschichtung. Hier bekommen Sie vollnarbiges Leder in Meisterqualität, das 10 Jahre hält. Wenn Sie heute kaufen, gebe ich Ihnen gerne 10% Rabatt und eine Lederpflege gratis.",
                            arabicTranslation = "أتفهم وجهة نظرك تماماً. الجاكيتات الأرخص تستخدم جلد صناعي أو مضغوط مطلي بالبلاستيك، بينما هذا جلد حبّة كاملة عالي الجودة يدوم 10 سنوات. وإذا ناسبك الشراء اليوم يسعدني تقديم خصم 10% مع كريم حماية الجلد مجاناً.",
                            arabicPhonetics = "إيش فيرشتيهه زي. بيليغِره ياكن نوتسن شبيلت-ليدر ميت بلاستيك-بِشيشتونغ. هير بيكومن زي فول-ناربيغِس ليدر إن مايستر-كفاليتيت. فين زي هويته كوفن، غيبه إيش إينن غيرنه تسين بروتسينت رابات."
                        ),
                        "en" to PhraseItem(
                            foreignText = "I completely understand. Cheaper jackets use pressed bonded leather. This is premium full-grain lambskin that lasts 10+ years. If you take it now, I can give you a special 10% tourist discount and a free leather balm.",
                            arabicTranslation = "أتفهمك تماماً. المنتجات الأرخص تستخدم جلدًا مضغوطًا ملصقًا. هذا جلد غنم أصلي كامل يدوم أكثر من 10 سنوات. إذا أخذته الآن، أمنحك خصماً خاصاً 10% مع علبة بلسم العناية مجاناً.",
                            arabicPhonetics = "آي كومبليتلي أندرستاند. تشيبر جاكيتس يوز بريسد ليذر. ذيس إز بريميوم فول-جرين."
                        )
                    )
                ).let {
                    PriceObjection(
                        id = it.id,
                        objectionAr = "السعر غالي مقارنة بمحلات أخرى",
                        objectionForeign = it.questionForeign,
                        response = it.answers,
                        salesTipAr = "اشرح الفرق بين جلد الحبة الكاملة والجلد المضغوط الرخيص، واعرض هدية إضافية (كريم عناية أو حزام صغير) بدلاً من خفض السعر بشكل كبير."
                    )
                },
                PriceObjection(
                    id = "obj_leather_thinking",
                    objectionAr = "سأفكر في الأمر وأعود لاحقاً",
                    objectionForeign = mapOf(
                        "de" to "Ich muss noch überlegen und komme vielleicht später wieder.",
                        "en" to "I need to think about it and might come back later.",
                        "ru" to "Я подумаю и, возможно, вернусь позже."
                    ),
                    response = mapOf(
                        "de" to PhraseItem(
                            foreignText = "Natürlich, nehmen Sie sich Zeit! Probieren Sie die Jacke nur einmal an, um das geringe Gewicht zu spüren – ganz unverbindlich.",
                            arabicTranslation = "بالتأكيد، خذ كامل وقتك! جرب فقط ارتداءها لثانية واحدة لتشعر بخفة وزنها وراحتها، دون أي التزام بالشراء.",
                            arabicPhonetics = "ناتورليش، نيمن زي زيش تسايت! بروبيرن زي دي ياكه نور آينمال آن، أوم داز غيرينغه غِفيشت تسو شبورن – غانتس أون-فيربيندليش."
                        ),
                        "en" to PhraseItem(
                            foreignText = "Of course, take your time! Just try it on for a second to feel the fit and weight—no pressure at all.",
                            arabicTranslation = "بالتأكيد، خذ كامل وقتك! فقط جربها لثوانٍ لتشعر بالمقاس وخفة الوزن - دون أي ضغط للشراء.",
                            arabicPhonetics = "أوف كورس، تيك يور تايم! جاست تراي إت أون."
                        )
                    ),
                    salesTipAr = "شجّع العميل على تجربة القطعة أمام المرآة؛ بمجرد ارتدائها ولمس خامتها ترتفع نسبة شرائها أضعافاً."
                )
            )
        ),

        // 2. Handcrafted Italian-Style Leather Bag
        Product(
            id = "leather_bag_02",
            nameAr = "حقيبة يد وسفر جلد طبيعي فاخر",
            nameNativeMap = mapOf(
                "de" to "Handgefertigte Ledertasche",
                "it" to "Borsa in Vera Pelle Artigianale",
                "ru" to "Кожаная сумка ручной работы",
                "en" to "Handcrafted Luxury Leather Bag",
                "fr" to "Sac en Cuir Artisanal de Luxe",
                "es" to "Bolso de Cuero Artesanal de Lujo"
            ),
            category = ProductCategory.LEATHER,
            iconEmoji = "👜",
            priceLocal = 3200.0,
            priceEur = 65.0,
            priceUsd = 70.0,
            verifiedFacts = listOf(
                "جلد بقر سميك مدبوغ بالزيوت الطبيعية (Pull-up leather)",
                "حزام كتف مقوّى قابل للتعديل مع بطانة مريحة",
                "سوست ومفاصل معدنية نحاسية مقاومة للصدأ 100%",
                "تتسع لجهاز لابتوب 15 بوصة مع جيوب حماية مبطنة"
            ),
            pitch = mapOf(
                "de" to PhraseItem(
                    foreignText = "Diese Tasche ist aus echtem Rindsleder mit massiven Messingbeschlägen gefertigt. Mit jedem Jahr bekommt das Leder eine noch schönere Patina.",
                    arabicTranslation = "هذه الحقيبة مصنوعة من جلد بقري طبيعي مع إكسسوارات نحاسية متينة. ومع مرور السنين يكتسب الجلد رونقاً ومظهراً كلاسيكياً أجمل.",
                    arabicPhonetics = "ديزه تاشه إست أوس إيشتيم ريندس-ليدر ميت ماسيفن ميسينغ-بِشليغن غِفرتيكت. ميت ييدم يار بيكومت داز ليدر آينه نوخ شونِره باتينا."
                ),
                "it" to PhraseItem(
                    foreignText = "Questa borsa è realizzata in pregiata pelle bovina conciata al vegetale. Gli accessori in ottone sono indistruttibili.",
                    arabicTranslation = "هذه الحقيبة مصنوعة من جلد بقر فاخر مدبوغ نباتياً، وإكسسواراتها النحاسية تدوم دون تلف.",
                    arabicPhonetics = "كويستا بورسا إيه ريالاتزاتا إن بريجياتا بيلّي بوفينا كونشياتا أل فيجيتالي."
                ),
                "en" to PhraseItem(
                    foreignText = "This bag is made from full-grain bovine leather with solid brass hardware. It develops a rich vintage patina as you use it.",
                    arabicTranslation = "هذه الحقيبة مصنوعة من جلد بقري أصلي كامل الحبة مع سحابات نحاسية صلبة، وتزداد جمالاً مع الاستخدام المستمر.",
                    arabicPhonetics = "ذيس باج إز ميد فروم فول-جرين بوفاين ليذر وذ سوليد براس هاردوير."
                )
            ),
            questions = listOf(
                ProductQuestion(
                    id = "q_bag_water",
                    questionAr = "هل الجلد يتأثر بالمطر أو قطرات الماء؟",
                    questionForeign = mapOf(
                        "de" to "Ist die Tasche wasserabweisend?",
                        "en" to "Is this bag water-resistant against rain?",
                        "ru" to "Защищена ли сумка от дождя и влаги?"
                    ),
                    answers = mapOf(
                        "de" to PhraseItem(
                            foreignText = "Ja, das Leder ist mit Naturölen gewachst. Regentropfen perlen einfach ab. Wischen Sie es einfach mit einem Tuch ab.",
                            arabicTranslation = "نعم، الجلد مشمع بزيوت طبيعية واقية، وقطرات المطر تنزلق دون أن تمتص. يكفي مسحها بقطعة قماش.",
                            arabicPhonetics = "يا، داز ليدر إست ميت ناتور-أويلن غِفاكست. ريغن-تروبفن بيرلن آينفاخ آب."
                        ),
                        "en" to PhraseItem(
                            foreignText = "Yes, it is treated with natural oils and wax. Water drops bead off easily.",
                            arabicTranslation = "نعم، تمت معالجته بزيوت وشمع طبيعي، وقطرات الماء تنزلق بسهولة دون ترك أثر.",
                            arabicPhonetics = "يَس، إت إز تريتد وذ ناتشرال أويلز أند واكس."
                        )
                    )
                )
            ),
            objections = listOf(
                PriceObjection(
                    id = "obj_bag_price",
                    objectionAr = "طلب تخفيض السعر",
                    objectionForeign = mapOf(
                        "de" to "Können Sie mir einen besseren Preis machen?",
                        "en" to "Can you give me a better discount?",
                        "ru" to "Сделаете ли вы мне скидку?"
                    ),
                    response = mapOf(
                        "de" to PhraseItem(
                            foreignText = "Der Preis ist bereits sehr fair für echtes Handwerk. Wenn Sie dazu eine Ledergeldbörse nehmen, gebe ich Ihnen das Set für einen Spezialpreis.",
                            arabicTranslation = "السعر عادل جداً مقابل هذه الصناعة اليدوية المتينة. وإذا أخذت معها محفظة جلدية سأعطيك الطقم بالكامل بسعر خاص جداً.",
                            arabicPhonetics = "دير برايس إست بارايتس زير فير فور إيشتِس هاندفيرك. فين زي داتسو آينه ليدر-غلد-بورزه نيمن، غيبه إيش إينن داز سيت فور آينن شبيتسيال-برايس."
                        ),
                        "en" to PhraseItem(
                            foreignText = "The price is already direct from our workshop. If you bundle it with a matching leather wallet, I will discount 15% on both.",
                            arabicTranslation = "السعر مباشر من ورشتنا دون وسيط. إذا أضفت محفظة مطابقة، سأمنحك خصماً 15% على القطعتين معاً.",
                            arabicPhonetics = "ذا برايس إز أولريدي دايركت فروم آور وورك شوب."
                        )
                    ),
                    salesTipAr = "اعرض حزمة (حقيبة + محفظة) لتحصل على قيمة بيع أعلى مع إرضاء العميل بالتخفيض."
                )
            )
        ),

        // 3. Royal Aged Oud & Amber Oil (محلات العطور والزيوت)
        Product(
            id = "perfume_oud_amber_03",
            nameAr = "زيت عطر العود الملكي والعنبر (خالٍ من الكحول)",
            nameNativeMap = mapOf(
                "de" to "Königliches Oud & Bernstein Parfümöl",
                "it" to "Olio di Profumo Oud Reale e Ambra",
                "ru" to "Королевское парфюмерное масло уд и амбра",
                "en" to "Royal Aged Oud & Amber Perfume Oil",
                "fr" to "Huile de Parfum Oud Royal et Ambre",
                "es" to "Aceite de Perfume Oud Real y Ámbar"
            ),
            category = ProductCategory.PERFUMES,
            iconEmoji = "✨",
            priceLocal = 1800.0,
            priceEur = 35.0,
            priceUsd = 40.0,
            verifiedFacts = listOf(
                "زيت عطري نقي 100% مركز خالي تماماً من الكحول والمذيبات",
                "ثبات ممتاز يدوم من 24 إلى 48 ساعة على الملابس والجلد",
                "آمن على البشرة الحساسة ولا يسبب أي تحسس أو بقع",
                "عبوة زجاجية كريستالية معتمدة ومطابقة لشروط أمتعة الطيران"
            ),
            pitch = mapOf(
                "de" to PhraseItem(
                    foreignText = "Dies ist ein reines, alkoholfreies Parfümöl aus echtem Oud und warmem Bernstein. Ein einziger Tropfen duftet über 24 Stunden.",
                    arabicTranslation = "هذا زيت عطري نقي وخالٍ تماماً من الكحول، مستخلص من العود الطبيعي والعنبر الدافئ. قطرة واحدة منه تدوم أكثر من 24 ساعة.",
                    arabicPhonetics = "ديز إست آين راينِس، ألكوهول-فرايِس بارفيوم-أويل أوس إيشتيم أود أوند فارميم بيرن-شتاين. آين آينتسيغر تروبفن دوفتت أوبر فير-أوند-تسفانتسيش شتوندن."
                ),
                "it" to PhraseItem(
                    foreignText = "Questo è un olio profumato puro, 100% senza alcool, a base di prezioso oud e ambra dorata. Persiste per oltre 24 ore.",
                    arabicTranslation = "هذا زيت عطر خالص 100% بدون كحول، برائحة العود الفاخر والعنبر الذهبي، يدوم أكثر من 24 ساعة.",
                    arabicPhonetics = "كويستو إيه أون أوليو بروفوماتو بورو، تشينتو بير تشينتو سينتسا ألكول، آ بازي دي أود إيه أمبرا."
                ),
                "ru" to PhraseItem(
                    foreignText = "Это чистое парфюмерное масло без спирта с натуральным удом и амброй. Всего одна капля держится более 24 часов.",
                    arabicTranslation = "هذا زيت عطري نقي بدون كحول مع العود الطبيعي والعنبر، قطرة واحدة تدوم أكثر من 24 ساعة.",
                    arabicPhonetics = "إيتو تشيستويه بارفيوميرنويه ماسلو بيز سبيرتا، فسيغو أدنا كابليا ديرجيتسيا بوليي دفاتساتي تشيتيريخ تشاسوف."
                ),
                "en" to PhraseItem(
                    foreignText = "This is a 100% alcohol-free concentrated perfume oil with aged royal oud and warm amber. Just a drop lasts for over 24 hours.",
                    arabicTranslation = "هذا زيت عطري مركز 100% خالٍ من الكحول مع العود الملكي المعتق والعنبر الدافئ، نقطة واحدة تدوم لأكثر من 24 ساعة.",
                    arabicPhonetics = "ذيس إز أ ون هاندرد بيرسنت ألكوهول-فري كونسنتريتد بيرفيوم أويل وذ إيدجد عود أند أمبر."
                )
            ),
            questions = listOf(
                ProductQuestion(
                    id = "q_perfume_skin",
                    questionAr = "هل يسبب حساسية للجلد أو يترك بقعاً على الملابس؟",
                    questionForeign = mapOf(
                        "de" to "Ist das Öl hautverträglich und hinterlässt es Flecken?",
                        "en" to "Is it safe for sensitive skin and does it stain clothes?",
                        "ru" to "Безопасно ли для чувствительной кожи и оставляет ли пятна?"
                    ),
                    answers = mapOf(
                        "de" to PhraseItem(
                            foreignText = "Es ist 100% naturbasiert ohne Alkohol oder Chemikalien, daher absolut sanft zur Haut. Auf die Pulspunkte aufgetragen hinterlässt es keine Flecken.",
                            arabicTranslation = "هو طبيعي 100% بدون كحول أو كيماويات، لذلك لطيف جداً على البشرة. وعند وضعه على أماكن النبض لا يترك أي بقع.",
                            arabicPhonetics = "إس إست هوندرت بروتسينت ناتور-بازيرت أونه ألكوهول أودر شيميكالين. أوف دي بولس-بونكته أوف-غي-تراغن هينتر-ليست إس كاينه فليكن."
                        ),
                        "en" to PhraseItem(
                            foreignText = "It is 100% alcohol-free and organic, completely hypoallergenic for skin. Apply it on pulse points for zero fabric staining.",
                            arabicTranslation = "هو عضوي وخالٍ من الكحول 100%، ولا يسبب أي تحسس للبشرة. يوضع على أماكن النبض دون أي تبقيع.",
                            arabicPhonetics = "إت إز ون هاندرد بيرسنت ألكوهول-فري أند أورجانيك."
                        )
                    )
                ),
                ProductQuestion(
                    id = "q_perfume_airplane",
                    questionAr = "هل يمكن حمله بأمان في حقيبة اليد داخل الطائرة؟",
                    questionForeign = mapOf(
                        "de" to "Darf ich das im Handgepäck im Flugzeug mitnehmen?",
                        "en" to "Can I take this bottle in my carry-on hand luggage on the airplane?",
                        "ru" to "Разрешено ли провозить это в ручной клади в самолете?"
                    ),
                    answers = mapOf(
                        "de" to PhraseItem(
                            foreignText = "Ja, die Flasche hat 50ml und ist fest versiegelt. Das entspricht genau den internationalen Handgepäck-Richtlinien (unter 100ml).",
                            arabicTranslation = "نعم، العبوة بحجم 50 مل ومحكمة الإغلاق تماماً، وهي مطابقة لشروط الطيران الدولي للحقيبة المحمولة (أقل من 100 مل).",
                            arabicPhonetics = "يا، دي فلاشه هات فونفتسيش ميلي-ليتر أوند إست فيست فير-زيغلت. داز إنتشبريشت غيناو دين إنترناتسيونالن ريشت-لينيين."
                        ),
                        "en" to PhraseItem(
                            foreignText = "Yes, it is 50ml, securely sealed and fully airport security compliant (well under the 100ml limit).",
                            arabicTranslation = "نعم، حجمها 50 مل، محكمة الإغلاق تماماً وموافقة لتعليمات أمن المطارات (أقل من الحد المسموح 100 مل).",
                            arabicPhonetics = "يَس، إت إز فيفتي مل، سيكيورلي سيلد أند كومبلاينت."
                        )
                    )
                )
            ),
            objections = listOf(
                PriceObjection(
                    id = "obj_perfume_small",
                    objectionAr = "الزجاجة تبدو صغيرة بالنسبة للسعر",
                    objectionForeign = mapOf(
                        "de" to "Die Flasche ist ziemlich klein für diesen Preis.",
                        "en" to "The bottle looks quite small for this price.",
                        "ru" to "Флакон кажется маленьким за эту цену."
                    ),
                    response = mapOf(
                        "de" to PhraseItem(
                            foreignText = "Das ist ein hochkonzentriertes Essenzöl, nicht mit Wasser oder Alkohol verdünnt wie normale Parfüms. Diese 50ml Flasche reicht bei täglicher Nutzung für über ein ganzes Jahr.",
                            arabicTranslation = "هذا زيت عطري خام فائق التركيز، وليس مخففاً بالماء أو الكحول كالعطور التجارية العادية. هذه الزجاجة الـ 50 مل تكفيك لأكثر من سنة كاملة من الاستخدام اليومي.",
                            arabicPhonetics = "داز إست آين هوخ-كونتسنتريرتِس إيسينتس-أويل، نيشت ميت فاسر أودر ألكوهول فيردونت. ديزه فلاشه رايشت فور أوبر آين غانتسِس يار."
                        ),
                        "en" to PhraseItem(
                            foreignText = "This is pure oil essence, not diluted with water or alcohol like regular sprays. One single bottle lasts over a full year of daily use.",
                            arabicTranslation = "هذا خلاصة زيت عطري صافٍ، غير مخفف كالبخاخات العادية. زجاجة واحدة تكفيك لأكثر من عام كامل بالاستخدام اليومي.",
                            arabicPhonetics = "ذيس إز بيور أويل إيسنس، نوت ديلوتد وذ ووتر أور ألكوهول."
                        )
                    ),
                    salesTipAr = "ضع نقطة صغيرة على يد العميل ليشتم تركيزها ويجرب قوتها، واشرح أن قطرة واحدة تغني عن 10 بخات من العطر العادي."
                )
            )
        ),

        // 4. Polarized Designer Sunglasses (محلات النظارات)
        Product(
            id = "sunglasses_polarized_04",
            nameAr = "نظارة شمسية بعدسات مستقطبة (Polarized UV400)",
            nameNativeMap = mapOf(
                "de" to "Polarisierte Sonnenbrille UV400",
                "it" to "Occhiali da Sole Polarizzati UV400",
                "ru" to "Поляризованные солнцезащитные очки UV400",
                "en" to "Polarized UV400 Sunglasses",
                "fr" to "Lunettes de Soleil Polarisées UV400",
                "es" to "Gafas de Sol Polarizadas UV400"
            ),
            category = ProductCategory.EYEWEAR,
            iconEmoji = "🕶️",
            priceLocal = 1600.0,
            priceEur = 32.0,
            priceUsd = 35.0,
            verifiedFacts = listOf(
                "عدسات مستقطبة Polarized تمنع الوهج وانعكاسات الشمس على الماء والسيارات",
                "حماية كاملة 100% من الأشعة فوق البنفسجية UV400 (UVA & UVB)",
                "إطار من التيتانيوم الخفيف جداً والمرن المقاوم للصدمات والكسر",
                "مرفقة بجراب صلب فاخر ومنديل مايكروفايبر أصلي للتنظيف"
            ),
            pitch = mapOf(
                "de" to PhraseItem(
                    foreignText = "Diese Brille hat echte polarisierte UV400-Gläser und einen ultraleichten Titanrahmen. Sie filtert jede Blendung und schützt Ihre Augen perfekt.",
                    arabicTranslation = "هذه النظارة مزودة بعدسات مستقطبة حقيقية مع حماية UV400 وإطار تيتانيوم فائق الخفة. تمنع كل الوهج وتحمي عينيك بالكامل.",
                    arabicPhonetics = "ديزه بريلّه هات إيشتِه بولاريزيرته أوفاو-فير-هوندرت غليزِر أوند آينن أولترا-لايشتن تيتان-رامن. زي فيلترت ييده بليندونغ."
                ),
                "it" to PhraseItem(
                    foreignText = "Questi occhiali montano lenti polarizzate UV400 con montatura in titanio leggerissima. Eliminano ogni riflesso fastidioso.",
                    arabicTranslation = "هذه النظارة تحتوي على عدسات مستقطبة UV400 مع إطار تيتانيوم فائق الخفة يمنع كل الانعكاسات المزعجة.",
                    arabicPhonetics = "كويستي أوكّيالي مونتانو لينتي بولاريتزاتي أوفاو كواترو-تشينتو كون مونتاتورا إن تيتانيو ليجيريسيما."
                ),
                "en" to PhraseItem(
                    foreignText = "These sunglasses feature genuine polarized UV400 lenses and an ultra-lightweight titanium frame. It cuts all glare and provides complete eye protection.",
                    arabicTranslation = "تتميز هذه النظارات بعدسات بولارايزد أصلية وحماية UV400 مع إطار تيتانيوم شديد الخفة يقضي على الوهج ويوفر حماية كاملة.",
                    arabicPhonetics = "ذيس صان-جلاسز فيتشر جينوين بولارايزد يو-في فور هاندرد لينسز."
                )
            ),
            questions = listOf(
                ProductQuestion(
                    id = "q_glasses_test",
                    questionAr = "كيف يتأكد العميل من أن العدسات بولارايزد حقيقية وليست تظليلاً عادياً؟",
                    questionForeign = mapOf(
                        "de" to "Woher weiß ich, dass die Gläser wirklich polarisiert sind?",
                        "en" to "How can I test that these lenses are genuinely polarized?",
                        "ru" to "Как проверить, что линзы действительно поляризованные?"
                    ),
                    answers = mapOf(
                        "de" to PhraseItem(
                            foreignText = "Schauen Sie bitte durch die Brille auf diese Testkarte: Das verborgene Bild wird sofort sichtbar. Das beweist den Pol-Filter.",
                            arabicTranslation = "تفضل بالنظر من خلال النظارة إلى بطاقة الاختبار هذه: ستظهر الصورة المخفية فوراً، وهذا يثبت وجود الفلتر المستقطب.",
                            arabicPhonetics = "شاون زي بيته دورش دي بريلّه أوف ديزه تيست-كارته: داز فيربورغِنه بيلد فيرد زوفورت زيشتبار. داز بيفايست دين بول-فيلتر."
                        ),
                        "en" to PhraseItem(
                            foreignText = "Please look at this polarization test card through the lenses: you will immediately see the hidden image appear.",
                            arabicTranslation = "تفضل بالنظر إلى كارت الاختبار عبر العدسات: سترى الصورة المخفية تظهر على الفور.",
                            arabicPhonetics = "بليز لوك أت ذيس بولارايزيشن تيست كارد ثرو ذا لينسز."
                        )
                    )
                )
            ),
            objections = listOf(
                PriceObjection(
                    id = "obj_glasses_cheap",
                    objectionAr = "يوجد نظارات في الشارع بسعر 5 يورو",
                    objectionForeign = mapOf(
                        "de" to "Auf der Straße gibt es Sonnenbrillen für 5 Euro.",
                        "en" to "Street vendors sell sunglasses for 5 dollars.",
                        "ru" to "Уличные торговцы продают очки за 5 евро."
                    ),
                    response = mapOf(
                        "de" to PhraseItem(
                            foreignText = "Billige Straßenbrillen haben nur dunkles Plastik ohne UV-Schutz – das weitet die Pupille und schadet den Augen. Diese Brille hat geprüfte UV400-Gläser zum Schutz Ihrer Sehkraft.",
                            arabicTranslation = "النظارات الرخيصة في الشارع هي مجرد بلاستيك داكن بدون حماية UV، مما يوسع بؤبؤ العين ويضر بالشبكية. هذه النظارة معتمدة بطبقة UV400 لحماية بصرك وسلامة عينيك.",
                            arabicPhonetics = "بيليغه شتراسن-بريلن هابن نور دونكلِس بلاستيك أونه أوفاو-شوتس. ديزه بريلّه هات غِبروفته أوفاو-فير-هوندرت غليزِر تسوم شوتس إيرِر زيه-كرافت."
                        ),
                        "en" to PhraseItem(
                            foreignText = "Cheap street glasses are just tinted plastic without UV protection, which actually damages your eyes. These have certified optical UV400 protection.",
                            arabicTranslation = "النظارات الرخيصة مجرد بلاستيك ملون بدون حماية، ما يضر بالعينين. أما هذه فتحتوي على حماية بصرية معتمدة UV400.",
                            arabicPhonetics = "تشيب ستريت جلاسز آر جاست تينتد بلاستيك وذاوت يو-في بروتكشن."
                        )
                    ),
                    salesTipAr = "ركز على الجانب الصحي لسلامة العينين، واعرض كارت الاختبار والجراب الصلب مع منديل التنظيف."
                )
            )
        ),

        // 5. Handwoven Cashmere & Pashmina Scarf (محلات التحف والهدايا)
        Product(
            id = "craft_pashmina_05",
            nameAr = "شال كشمير وباشمينا يدوي ناعم",
            nameNativeMap = mapOf(
                "de" to "Handgewebter Kaschmir- & Paschmina-Schal",
                "it" to "Sciarpa Artigianale in Cashmere e Pashmina",
                "ru" to "Кашемировый палантин ручной работы",
                "en" to "Handwoven Cashmere & Pashmina Scarf",
                "fr" to "Écharpe Tissée Main en Cachemire et Pashmina",
                "es" to "Bufanda Tejida a Mano de Cachemira y Pashmina"
            ),
            category = ProductCategory.CRAFTS,
            iconEmoji = "🧣",
            priceLocal = 950.0,
            priceEur = 20.0,
            priceUsd = 22.0,
            verifiedFacts = listOf(
                "صوف كشمير طبيعي ناعم جداً وخفيف الوزن",
                "منسوج على النول اليدوي التقليدي بخيوط حريرية متداخلة",
                "يدفئ في الشتاء ويبقى بارداً ولطيفاً في الصيف",
                "يمر بسلاسة من داخل خاتم إصبع صغير لدلالة أصالته ونعومته"
            ),
            pitch = mapOf(
                "de" to PhraseItem(
                    foreignText = "Dieser Schal ist auf traditionellen Holzwebstühlen aus 100% feinstem Kaschmir handgewebt. Fühlen Sie die unvergleichliche Weichheit.",
                    arabicTranslation = "هذا الشال منسوج يدوياً على أنوال خشبية تقليدية من أجود صوف الكشمير الطبيعي. تفضل بلمس هذه النعومة الاستثنائية.",
                    arabicPhonetics = "ديزر شال إست أوف تراديتسيونيلن هولتس-فيب-شتولن أوس هوندرت بروتسينت فاينستيم كاشمير هاند-غِفيفت. فولن زي دي أون-فيرغلايش-ليشه فايش-كايت."
                ),
                "en" to PhraseItem(
                    foreignText = "This scarf is handwoven on traditional looms from 100% fine cashmere. Feel the remarkable softness—it even passes smoothly through a small ring.",
                    arabicTranslation = "هذا الشال منسوج يدويًا على أنوال تقليدية من الكشمير الطبيعي الناعم. المس النعومة الرائعة - إنه يمر حتى من خلال خاتم إصبع صغير.",
                    arabicPhonetics = "ذيس سكارف إز هاند-ووفن أون تراديشنال لومز فروم ون هاندرد بيرسنت فاين كاشمير."
                )
            ),
            questions = listOf(
                ProductQuestion(
                    id = "q_pashmina_wash",
                    questionAr = "طريقة الغسيل والعناية بالشال في المنزل؟",
                    questionForeign = mapOf(
                        "de" to "Wie wasche und pflege ich diesen Kaschmirschal?",
                        "en" to "How should I wash and care for this cashmere scarf?",
                        "ru" to "Как стирать и ухаживать за этим шарфом?"
                    ),
                    answers = mapOf(
                        "de" to PhraseItem(
                            foreignText = "Einfach Handwäsche mit kaltem Wasser und einem Tropfen Wollwaschmittel oder Haarshampoo, flach auf einem Handtuch trocknen.",
                            arabicTranslation = "غسيل يدوي بسيط بالماء البارد مع قطرة شامبو لطيف، وتجفيفه منبسطاً على منشفة دون عصر.",
                            arabicPhonetics = "آينفاخ هاند-فيشه ميت كالتيم فاسر أوند آينم تروبفن فول-فاش-ميتل، فلاش أوف آينم هاند-توخ تروكنن."
                        ),
                        "en" to PhraseItem(
                            foreignText = "Gentle hand wash in cold water with mild shampoo, then lay it flat on a dry towel to dry naturally.",
                            arabicTranslation = "غسيل يدوي رقيق بالماء البارد مع شامبو لطيف، ثم فرده على منشفة جافة ليجف طبيعياً.",
                            arabicPhonetics = "جينتل هاند واش إن كولد ووتر وذ مايلد شامبو."
                        )
                    )
                )
            ),
            objections = listOf(
                PriceObjection(
                    id = "obj_pashmina_discount",
                    objectionAr = "طلب خصم عند شراء أكثر من قطعة كهدايا",
                    objectionForeign = mapOf(
                        "de" to "Bekomme ich Rabatt, wenn ich 3 Stück als Geschenke nehme?",
                        "en" to "Can I get a discount if I take 3 pieces as souvenirs?",
                        "ru" to "Сделаете ли скидку, если я возьму 3 штуки на подарки?"
                    ),
                    response = mapOf(
                        "de" to PhraseItem(
                            foreignText = "Sehr gerne! Wenn Sie drei Schals nehmen, bekommen Sie den dritten zum halben Preis, plus hübsche Geschenkverpackungen dazu.",
                            arabicTranslation = "بكل سرور! إذا أخذت 3 شالات، ستحصل على الثالث بنصف السعر، مع تغليف هدايا أنيق مجاناً.",
                            arabicPhonetics = "زير غيرنه! فين زي دراي شالس نيمن، بيكومن زي دين دريتن تسوم هالبن برايس، بلوس هوبشه غِشينك-فيرباكونغن داتسو."
                        ),
                        "en" to PhraseItem(
                            foreignText = "With pleasure! If you choose three scarves, you get the third one for 50% off plus free authentic gift packaging.",
                            arabicTranslation = "بكل سرور! إذا اخترت ثلاثة شالات، تحصل على الثالث بخصم 50% مع تغليف هدايا تراثي أنيق مجاناً.",
                            arabicPhonetics = "وذ بليجر! إف يو تشوز ثري سكارفز، يو غت ذا ثيرد ون فور فيفتي بيرسنت أوف."
                        )
                    ),
                    salesTipAr = "الشالات والتحف هي هدايا سياحية مثالية، بيع العروض المجمعة (3 قطع أو 5 قطع) يرفع مبيعاتك بشكل ملموس."
                )
            )
        )
    )

    val QUICK_PHRASES: List<com.example.data.model.QuickPhrase> = listOf(
        com.example.data.model.QuickPhrase(
            id = "qp_welcome_01",
            category = com.example.data.model.PhraseCategory.WELCOME,
            titleAr = "ترحيب دافئ ودعوة للتفرج بحرية",
            translations = mapOf(
                "de" to PhraseItem(
                    foreignText = "Herzlich willkommen! Schauen Sie sich ganz in Ruhe um und fühlen Sie gerne die Qualität.",
                    arabicTranslation = "أهلاً وسهلاً بكم! تفضلوا بالتجول براحتكم ولمس الخامات والاطلاع على الجودة.",
                    arabicPhonetics = "هيرتس-ليش فيل-كومن! شاون زي زيش غانتس إن روهه أوم أوند فولن زي غيرنه دي كفاليتيت."
                ),
                "en" to PhraseItem(
                    foreignText = "Welcome! Feel free to look around and touch the quality at your own pace.",
                    arabicTranslation = "أهلاً بك! خذ كامل راحتك في النظر ولمس المنتجات.",
                    arabicPhonetics = "ويلكم! فيل فري تو لوك أراوند."
                ),
                "it" to PhraseItem(
                    foreignText = "Benvenuti! Guardate pure con tutta calma e toccate con mano la qualità.",
                    arabicTranslation = "أهلاً بكم! تفضلوا بالمشاهدة براحة ولمس الجودة بأيديكم.",
                    arabicPhonetics = "بينفينوتي! غوارداتي بوري كون توتا كالمة."
                ),
                "ru" to PhraseItem(
                    foreignText = "Добро пожаловать! Смотрите спокойно, трогайте и оценивайте качество.",
                    arabicTranslation = "أهلاً وسهلاً! تفضلوا بالمشاهدة براحة وتحسس الجودة.",
                    arabicPhonetics = "دوبرو بوجالوفات! سموتريتي سبوكوينو."
                )
            )
        ),
        com.example.data.model.QuickPhrase(
            id = "qp_fit_01",
            category = com.example.data.model.PhraseCategory.TRY_FIT,
            titleAr = "دعوة لتجربة المقاس والمرآة",
            translations = mapOf(
                "de" to PhraseItem(
                    foreignText = "Probieren Sie es gerne an! Hier ist der Spiegel und gutes Licht. Wie fühlt es sich an?",
                    arabicTranslation = "تفضل بتجربته في الحال! المرآة والإضاءة الممتازة هنا، كيف تشعر به؟",
                    arabicPhonetics = "بروبيرن زي إس غيرنه آن! هير إست دير شبيغل أوند غوتِس ليشت. في فولت إس زيش آن؟"
                ),
                "en" to PhraseItem(
                    foreignText = "Please try it on! Here is the mirror. How does the fit feel?",
                    arabicTranslation = "تفضل بتجربته! هذه هي المرآة، كيف تجد المقاس؟",
                    arabicPhonetics = "بليز تراي إت أون! هير إز ذا ميرور."
                )
            )
        ),
        com.example.data.model.QuickPhrase(
            id = "qp_pay_01",
            category = com.example.data.model.PhraseCategory.PAYMENT,
            titleAr = "طرق الدفع والعملات المقبولة",
            translations = mapOf(
                "de" to PhraseItem(
                    foreignText = "Sie können bequem in Euro, US-Dollar oder mit Kreditkarte (Visa/Mastercard) bezahlen.",
                    arabicTranslation = "يمكنك الدفع بكل سهولة باليورو أو الدولار أو بالبطاقة البنكية فيزا وماستركارد.",
                    arabicPhonetics = "زي كونن بيكفيم إن أويغو، أوس-دولار أودر ميت كريديت-كارته بيتسالن."
                ),
                "en" to PhraseItem(
                    foreignText = "You can pay comfortably in Euros, US Dollars, or by credit card (Visa/Mastercard).",
                    arabicTranslation = "يمكنك الدفع بكل راحة باليورو أو الدولار أو البطاقة الائتمانية.",
                    arabicPhonetics = "يو كان باي كومفرتابلي إن يوروز أور يو إس دولاز."
                )
            )
        ),
        com.example.data.model.QuickPhrase(
            id = "qp_guarantee_01",
            category = com.example.data.model.PhraseCategory.GUARANTEE,
            titleAr = "التغليف الآمن للسفر وشهادة الضمان",
            translations = mapOf(
                "de" to PhraseItem(
                    foreignText = "Wir verpacken alles sicher und luftdicht für Ihren Flug, inklusive internationaler Garantiekarte.",
                    arabicTranslation = "سنقوم بتغليف كل شيء بعناية وحمايته لرحلة الطيران، مع بطاقة الضمان الدولية.",
                    arabicPhonetics = "فير فيرباكن أليس زيشر أوند لوفت-ديشت فور إيرن فلوغ، إنكلوتزيفه إنترناتسيونالِر غارانتيه-كارته."
                ),
                "en" to PhraseItem(
                    foreignText = "We pack everything securely for your flight, with our international guarantee card included.",
                    arabicTranslation = "نغلف كل شيء بأمان لرحلتك الجوية مع شهادة الضمان الدولية.",
                    arabicPhonetics = "وي باك إفريثينغ سيكيورلي فور يور فلايت."
                )
            )
        ),
        com.example.data.model.QuickPhrase(
            id = "qp_closing_01",
            category = com.example.data.model.PhraseCategory.CLOSING,
            titleAr = "شكر دافئ وتوديع مبارك للرحلة",
            translations = mapOf(
                "de" to PhraseItem(
                    foreignText = "Vielen herzlichen Dank! Ich wünsche Ihnen noch einen wunderschönen Urlaub und eine gute Heimreise!",
                    arabicTranslation = "شكراً جزيلاً لكم من القلب! أتمنى لكم إجازة رائعة ورحلة عودة ميمونة وسعيدة!",
                    arabicPhonetics = "فيلن هيرتس-ليشن دانك! إيش فونشه إينن نوخ آينن فوندر-شونن أورلاوب أوند آينه غوته هايم-رايزه!"
                ),
                "en" to PhraseItem(
                    foreignText = "Thank you very much! Have a wonderful vacation and a safe flight home!",
                    arabicTranslation = "شكراً جزيلاً لك! أتمنى لك عطلة رائعة ورحلة عودة سالمة!",
                    arabicPhonetics = "ثانك يو فيري ماتش! هاف أ وندرفول فاكيشن."
                )
            )
        )
    )
}
