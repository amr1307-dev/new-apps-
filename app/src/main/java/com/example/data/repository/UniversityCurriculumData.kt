package com.example.data.repository

import com.example.data.model.AcademicLevel
import com.example.data.model.DialogueLine
import com.example.data.model.GrammarRule
import com.example.data.model.InteractiveLabScenario
import com.example.data.model.Lesson
import com.example.data.model.QuestionType
import com.example.data.model.QuizQuestion
import com.example.data.model.VocabWord

object UniversityCurriculumData {

    val LESSONS: List<Lesson> = listOf(
        // ================= GERMAN TRACK (DE) =================
        Lesson(
            id = "de_a1_01",
            level = AcademicLevel.A1,
            trackCode = "de",
            orderNumber = 1,
            titleAr = "التعارف، التحية، والتقديم الرسمي",
            titleForeign = "Begrüßung & Sich vorstellen",
            estimatedMinutes = 10,
            lectureConceptAr = "في هذه المحاضرة التأسيسية تتعلم كيف تقدم نفسك بثقة كطالب جامعي أو مقيم باللغة الألمانية، وتصرف أفعال الهوية الأساسية دون أخطاء شائعة.",
            grammarRule = GrammarRule(
                titleAr = "قاعدة تصريف أفعال الهوية (sein & heißen)",
                explanationAr = "في الألمانية يأتي الفعل دائماً في المركز الثاني في الجملة الخبرية. يتغير شكل الفعل حسب الضمير.",
                formulaAr = "الفاعل + الفعل المصرف + باقي الجملة (Subjekt + Verb + Rest)",
                practicalExampleForeign = "Ich heiße Omar und ich bin Student an der Universität.",
                practicalExampleArabic = "أنا اسمي عمر وأنا طالب في الجامعة."
            ),
            vocabulary = listOf(
                VocabWord(
                    id = "v_de_01",
                    foreignWord = "Guten Tag",
                    arabicMeaning = "نهارك سعيد / مرحباً",
                    arabicPhonetics = "غوتِن تاغ",
                    exampleSentence = "Guten Tag, wie geht es Ihnen?",
                    exampleTranslation = "نهارك سعيد، كيف حال حضرتك؟"
                ),
                VocabWord(
                    id = "v_de_02",
                    foreignWord = "Ich heiße...",
                    arabicMeaning = "اسمي...",
                    arabicPhonetics = "إيش هايسّه...",
                    exampleSentence = "Ich heiße Karim und komme aus Ägypten.",
                    exampleTranslation = "اسمي كريم وقادم من مصر."
                ),
                VocabWord(
                    id = "v_de_03",
                    foreignWord = "Freut mich",
                    arabicMeaning = "سعيد بمعرفتك / تشرفنا",
                    arabicPhonetics = "فرويت ميش",
                    exampleSentence = "Sehr angenehm, freut mich sehr!",
                    exampleTranslation = "فرصة سعيدة جداً، تشرفت بمعرفتك!"
                ),
                VocabWord(
                    id = "v_de_04",
                    foreignWord = "Woher kommen Sie?",
                    arabicMeaning = "من أين حضرتك؟ (صيغة الاحترام)",
                    arabicPhonetics = "فوهير كومِن زي؟",
                    exampleSentence = "Woher kommen Sie genau?",
                    exampleTranslation = "من أين حضرتك بالتحديد؟"
                ),
                VocabWord(
                    id = "v_de_05",
                    foreignWord = "Auf Wiedersehen",
                    arabicMeaning = "إلى اللقاء / مع السلامة",
                    arabicPhonetics = "أوف فيدر-زيهن",
                    exampleSentence = "Vielen Dank und auf Wiedersehen!",
                    exampleTranslation = "شكراً جزيلاً وإلى اللقاء!"
                )
            ),
            conversationDialogue = listOf(
                DialogueLine(
                    speakerName = "المحاضر د. شنايدر",
                    isUser = false,
                    foreignText = "Guten Tag! Willkommen an unserer Sprachakademie. Wie heißen Sie?",
                    arabicTranslation = "نهارك سعيد! مرحباً بك في أكاديمية اللغات. ما اسمك؟",
                    arabicPhonetics = "غوتِن تاغ! فيلكومن آن أونزرِر شبراخ-أكاديميه. في هايسن زي؟"
                ),
                DialogueLine(
                    speakerName = "أنت (الطالب)",
                    isUser = true,
                    foreignText = "Guten Tag Herr Schneider! Ich heiße Tarek und ich lerne Deutsch für mein Studium.",
                    arabicTranslation = "نهارك سعيد سيد شنايدر! اسمي طارق وأتعلم الألمانية من أجل دراستي الجامعية.",
                    arabicPhonetics = "غوتِن تاغ هير شنايدر! إيش هايسه طارق أوند إيش ليرنه دويتش فور ماين شتوديوم."
                ),
                DialogueLine(
                    speakerName = "المحاضر د. شنايدر",
                    isUser = false,
                    foreignText = "Sehr schön, freut mich! Sprechen Sie schon etwas Deutsch?",
                    arabicTranslation = "جميل جداً، تشرفت بك! هل تتحدث القليل من الألمانية بالفعل؟",
                    arabicPhonetics = "زير شون، فرويت ميش! شبريشن زي شون إتفاس دويتش؟"
                ),
                DialogueLine(
                    speakerName = "أنت (الطالب)",
                    isUser = true,
                    foreignText = "Ja, ein bisschen. Ich übe jeden Tag mit meiner Taschen-Universität!",
                    arabicTranslation = "نعم، قليلاً. أتدرب يومياً مع جامعتي في الجيب!",
                    arabicPhonetics = "يا، آين بيسشن. إيش أوبه ييدن تاغ ميت ماينر تاشن-أونيفرزيتيت!"
                )
            ),
            quizQuestions = listOf(
                QuizQuestion(
                    id = "q_de_a1_01",
                    promptAr = "كيف تقدم نفسك بالألمانية وتقول 'اسمي سامي'؟",
                    promptForeign = "Ich ... Sami.",
                    options = listOf("heiße", "komme", "wohne", "lerne"),
                    correctIndex = 0,
                    explanationAr = "الفعل 'heißen' يعني يُسمى، ومع الضمير Ich يأخذ النهاية -e فيكون 'Ich heiße'."
                ),
                QuizQuestion(
                    id = "q_de_a1_02",
                    promptAr = "ما هو المعنى الصحيح للعبارة الألمانية 'Woher kommen Sie؟'؟",
                    promptForeign = "Woher kommen Sie?",
                    options = listOf("من أين حضرتك؟", "أين تسكن حالياً؟", "كم عمر حضرتك؟", "ماذا تعمل؟"),
                    correctIndex = 0,
                    explanationAr = "أداة الاستفهام 'Woher' تعني (من أين) وتستخدم للسؤال عن الموطن والمنشأ."
                ),
                QuizQuestion(
                    id = "q_de_a1_03",
                    promptAr = "ما هي العبارة المهذبة التي تقال عند التعارف وتعني 'تشرفنا / سعيد بلقائك'؟",
                    promptForeign = "Nice to meet you",
                    options = listOf("Freut mich", "Auf Wiedersehen", "Gute Nacht", "Entschuldigung"),
                    correctIndex = 0,
                    explanationAr = "'Freut mich' هي العبارة القياسية الألمانية التي تعبر عن سرور اللقاء والتعارف."
                )
            )
        ),

        Lesson(
            id = "de_a2_02",
            level = AcademicLevel.A2,
            trackCode = "de",
            orderNumber = 2,
            titleAr = "في المطعم وطلب الوجبات بدقة وأدب",
            titleForeign = "Im Restaurant & Höflich bestellen",
            estimatedMinutes = 12,
            lectureConceptAr = "تعلم صياغة الطلب الراقي بأسلوب Konjunktiv II المهذب، وحساب الفاتورة، والتعبير عن الإعجاب بجودة الخدمة.",
            grammarRule = GrammarRule(
                titleAr = "أسلوب الطلب المهذب باستخدام (hätte gern & möchte)",
                explanationAr = "بدلاً من قول 'Ich will' (أنا أريد بفظاظة)، يستخدم المتحدث الطليق 'Ich hätte gern' أو 'Ich möchte'.",
                formulaAr = "Ich hätte gern + اسم الشيء المنصوب (Akkusativ)",
                practicalExampleForeign = "Ich hätte gern einen Kaffee mit Milch und die Rechnung bitte.",
                practicalExampleArabic = "أرغب بلطف في فنجان قهوة بالحليب، والفاتورة من فضلك."
            ),
            vocabulary = listOf(
                VocabWord(
                    id = "v_de_06",
                    foreignWord = "Ich hätte gern...",
                    arabicMeaning = "أرغب بلطف في الحصول على...",
                    arabicPhonetics = "إيش هِتّه غيرن...",
                    exampleSentence = "Ich hätte gern ein Glas Mineralwasser.",
                    exampleTranslation = "أرغب بلطف في كأس ماء معدني."
                ),
                VocabWord(
                    id = "v_de_07",
                    foreignWord = "Die Speisekarte",
                    arabicMeaning = "قائمة الطعام والوجبات",
                    arabicPhonetics = "دي شبايزه-كارته",
                    exampleSentence = "Könnten wir bitte die Speisekarte haben?",
                    exampleTranslation = "هل يمكننا الحصول على قائمة الطعام من فضلك؟"
                ),
                VocabWord(
                    id = "v_de_08",
                    foreignWord = "Zusammen oder getrennt?",
                    arabicMeaning = "الحساب معاً أم منفصلاً لكل شخص؟",
                    arabicPhonetics = "تسو-زامن أودر غِترينت؟",
                    exampleSentence = "Zahlen Sie zusammen oder getrennt?",
                    exampleTranslation = "هل تدفعون معاً أم كل شخص على حدة؟"
                ),
                VocabWord(
                    id = "v_de_09",
                    foreignWord = "Stimmt so",
                    arabicMeaning = "الباقي لك (إكرامية / بقشيش مهذب)",
                    arabicPhonetics = "شتيمت زو",
                    exampleSentence = "Hier sind 20 Euro, stimmt so!",
                    exampleTranslation = "تفضل 20 يورو، والباقي لحضرتك!"
                )
            ),
            conversationDialogue = listOf(
                DialogueLine(
                    speakerName = "النادل (Kellner)",
                    isUser = false,
                    foreignText = "Guten Abend! Haben Sie schon gewählt oder möchten Sie eine Empfehlung?",
                    arabicTranslation = "مساء الخير! هل اخترتم بالفعل أم ترغبون في توصية الشيف؟",
                    arabicPhonetics = "غوتِن آبند! هابن زي شون غيفيلت أودر موشتن زي آينه إمبفيلونغ؟"
                ),
                DialogueLine(
                    speakerName = "أنت (الزبون)",
                    isUser = true,
                    foreignText = "Guten Abend! Ich hätte gern das Tagesgericht und einen frischen Apfelsaft.",
                    arabicTranslation = "مساء الخير! أرغب في وجبة اليوم وعصير تفاح طازج من فضلك.",
                    arabicPhonetics = "غوتِن آبند! إيش هِته غيرن داز تاغِس-غِريشت أوند آينن فريشن أبفل-زافت."
                ),
                DialogueLine(
                    speakerName = "النادل (Kellner)",
                    isUser = false,
                    foreignText = "Sehr gerne! Hat es Ihnen geschmeckt?",
                    arabicTranslation = "بكل سرور! هل نال الطعام إعجابكم؟",
                    arabicPhonetics = "زير غيرنه! هات إس إينن غِشماكت؟"
                ),
                DialogueLine(
                    speakerName = "أنت (الزبون)",
                    isUser = true,
                    foreignText = "Ja, ausgezeichnet! Wir möchten bitte zahlen.",
                    arabicTranslation = "نعم، كان ممتازاً للغاية! نود دفع الحساب من فضلك.",
                    arabicPhonetics = "يا، أوس-غِتسايخنيت! فير موشتن بيته تسالن."
                )
            ),
            quizQuestions = listOf(
                QuizQuestion(
                    id = "q_de_a2_01",
                    promptAr = "ما هي العبارة المهذبة والمثالية لطلب وجبة في مطعم بألمانيا؟",
                    promptForeign = "Ordering food politely",
                    options = listOf("Ich hätte gern...", "Ich will sofort...", "Gib mir...", "Warum nicht..."),
                    correctIndex = 0,
                    explanationAr = "'Ich hätte gern' هي أرقى صيغة للطلب المهذب وتظهر طلاقة أدبية عالية."
                ),
                QuizQuestion(
                    id = "q_de_a2_02",
                    promptAr = "ماذا تعني عبارة النادل الشهيرة 'Zusammen oder getrennt؟' عند دفع الحساب؟",
                    promptForeign = "Payment question",
                    options = listOf("الحساب معاً أم منفصلاً؟", "نقداً أم بالبطاقة؟", "داخلي أم سفري؟", "بالشاي أم القهوة؟"),
                    correctIndex = 0,
                    explanationAr = "في الثقافة الألمانية غالباً ما يسأل النادل عما إذا كان الضيوف سيدفعون مجتمعين (Zusammen) أو كل فرد حصته (Getrennt)."
                )
            )
        ),

        Lesson(
            id = "de_b1_03",
            level = AcademicLevel.B1,
            trackCode = "de",
            orderNumber = 3,
            titleAr = "السفر، التنقل، وإدارة المواقف الطارئة",
            titleForeign = "Reisen, Verkehr & Probleme lösen",
            estimatedMinutes = 14,
            lectureConceptAr = "اتقان الاستفسار عن القطارات، حجز الفنادق، وحل المشكلات غير المتوقعة بطلاقة ودون خوف من الخطأ.",
            grammarRule = GrammarRule(
                titleAr = "حروف الجر المكانية المشتركة (Wechselpräpositionen)",
                explanationAr = "حروف مثل (in, an, auf) تأخذ حالة الأكوزاتيف (النصب) عند وجود حركة وتغيير مكان، وتأخذ الداتيف (المجرور) عند السكون والثبات.",
                formulaAr = "حركة (Wohin? -> Akkusativ) | سكون (Wo? -> Dativ)",
                practicalExampleForeign = "Ich fahre an den Bahnhof (Akkusativ) - Ich bin am Bahnhof (Dativ).",
                practicalExampleArabic = "أنا ذاهب إلى المحطة (حركة) - أنا متواجد في المحطة (سكون)."
            ),
            vocabulary = listOf(
                VocabWord(
                    id = "v_de_10",
                    foreignWord = "Der Anschlusszug",
                    arabicMeaning = "قطار التبديل / المواصلة التالية",
                    arabicPhonetics = "دير آن-شلوس-تسوغ",
                    exampleSentence = "Erreiche ich noch meinen Anschlusszug nach München?",
                    exampleTranslation = "هل سألحق بقطار المواصلة إلى ميونخ؟"
                ),
                VocabWord(
                    id = "v_de_11",
                    foreignWord = "Die Verspätung",
                    arabicMeaning = "التأخير في الموعد",
                    arabicPhonetics = "دي فير-شبيتونغ",
                    exampleSentence = "Der Zug hat leider 20 Minuten Verspätung.",
                    exampleTranslation = "للأسف القطار به تأخير 20 دقيقة."
                ),
                VocabWord(
                    id = "v_de_12",
                    foreignWord = "Das Einzelzimmer",
                    arabicMeaning = "غرفة فندقية لشخص واحد",
                    arabicPhonetics = "داز آينتسل-تسيمر",
                    exampleSentence = "Ich habe ein Einzelzimmer mit Frühstück reserviert.",
                    exampleTranslation = "حجزت غرفة مفردة شاملة وجبة الإفطار."
                )
            ),
            conversationDialogue = listOf(
                DialogueLine(
                    speakerName = "موظف الاستعلامات (Bahn-Mitarbeiter)",
                    isUser = false,
                    foreignText = "Guten Tag! Kann ich Ihnen bei der Reiseverbindung behilflich sein?",
                    arabicTranslation = "نهارك سعيد! هل يمكنني مساعدتك بخصوص مواعيد رحلتك؟",
                    arabicPhonetics = "غوتِن تاغ! كان إيش إينن باي دير رايزه-فيربيندونغ بِهيلف-ليش زاين؟"
                ),
                DialogueLine(
                    speakerName = "أنت (المسافر)",
                    isUser = true,
                    foreignText = "Ja bitte. Mein Flug hatte Verspätung. Von welchem Gleis fährt der nächste ICE nach Frankfurt?",
                    arabicTranslation = "نعم من فضلك. طائرتي تأخرت، من أي رصيف يغادر قطار الـ ICE القادم إلى فرانكفورت؟",
                    arabicPhonetics = "يا بيته. ماين فلوغ هاتّه فيرشبيتونغ. فون فيلشِم غلايس فيرت دير نِكسته إيتسه-إيه ناخ فرانكفورت؟"
                )
            ),
            quizQuestions = listOf(
                QuizQuestion(
                    id = "q_de_b1_01",
                    promptAr = "ماذا يعني مصطلح 'Das Gleis' في محطات القطارات الألمانية؟",
                    promptForeign = "Train station term",
                    options = listOf("رصيف القطار", "تذكرة السفر", "عربة النوم", "شباك التذاكر"),
                    correctIndex = 0,
                    explanationAr = "'Gleis' تعني رصيف وسكة القطار التي يقف عليها القطار المحدد."
                )
            )
        ),

        // ================= ENGLISH TRACK (EN) =================
        Lesson(
            id = "en_a1_01",
            level = AcademicLevel.A1,
            trackCode = "en",
            orderNumber = 1,
            titleAr = "التحدث بثقة وبناء الهوية الشخصية بالإنجليزية",
            titleForeign = "Confident Self-Introduction & Fluency",
            estimatedMinutes = 10,
            lectureConceptAr = "تعلم كيف تتحدث عن نفسك وخلفيتك وأهدافك بطلاقة وبنبرة صوت طبيعية تجذب المستمع دون تردد.",
            grammarRule = GrammarRule(
                titleAr = "The Present Simple for Habits & Facts",
                explanationAr = "يستخدم المضارع البسيط للحقائق الثابتة والروتين اليومي. نضيف s للمفرد الغائب (He, She, It).",
                formulaAr = "Subject + Base Verb (+s with he/she/it)",
                practicalExampleForeign = "I study languages every day and I work in business.",
                practicalExampleArabic = "أنا أدرس اللغات كل يوم وأعمل في مجال الأعمال."
            ),
            vocabulary = listOf(
                VocabWord(
                    id = "v_en_01",
                    foreignWord = "I am passionate about...",
                    arabicMeaning = "أنا شغوف بـ / مهتم جداً بـ...",
                    arabicPhonetics = "آي آم باشيونيت أباوت...",
                    exampleSentence = "I am passionate about learning languages and exploring cultures.",
                    exampleTranslation = "أنا شغوف بتعلم اللغات واستكشاف الثقافات."
                ),
                VocabWord(
                    id = "v_en_02",
                    foreignWord = "In my spare time",
                    arabicMeaning = "في وقت فراغي",
                    arabicPhonetics = "إن ماي سبير تايم",
                    exampleSentence = "In my spare time, I practice conversational English.",
                    exampleTranslation = "في وقت فراغي، أمارس المحادثة باللغة الإنجليزية."
                ),
                VocabWord(
                    id = "v_en_03",
                    foreignWord = "Could you please clarify?",
                    arabicMeaning = "هل يمكنك توضيح ذلك من فضلك؟",
                    arabicPhonetics = "كود يو بليز كلاريفاي؟",
                    exampleSentence = "Could you please clarify that point for me?",
                    exampleTranslation = "هل يمكنك توضيح هذه النقطة لي من فضلك؟"
                )
            ),
            conversationDialogue = listOf(
                DialogueLine(
                    speakerName = "المدرب الجامعي ديفيد",
                    isUser = false,
                    foreignText = "Hello and welcome! Tell me a bit about yourself and why you're learning English.",
                    arabicTranslation = "مرحباً وأهلاً بك! أخبرني قليلاً عن نفسك ولماذا تتعلم الإنجليزية.",
                    arabicPhonetics = "هلو أند ويلكم! تيل مي أ بت أباوت يورسيلف."
                ),
                DialogueLine(
                    speakerName = "أنت (الطالب)",
                    isUser = true,
                    foreignText = "Hi David! I'm learning English to expand my career opportunities and speak fluently without hesitation.",
                    arabicTranslation = "أهلاً ديفيد! أتعلم الإنجليزية لتوسيع فرَصي المهنية والتحدث بطلاقة دون تردد.",
                    arabicPhonetics = "هاي ديفيد! آي آم ليرنينغ إنجلش تو إكسباند ماي كاريير أوبورتيونيتيز."
                )
            ),
            quizQuestions = listOf(
                QuizQuestion(
                    id = "q_en_a1_01",
                    promptAr = "اختر العبارة المهنية الأكثر ملاءمة للتعبير عن الشغف بمجال دراستك:",
                    promptForeign = "Expressing deep interest",
                    options = listOf("I am passionate about...", "I just do it", "I don't care much", "Maybe later"),
                    correctIndex = 0,
                    explanationAr = "'I am passionate about' تعبر عن حماس واحترافية عالية في المقابلات والتقديم."
                )
            )
        ),

        // ================= ITALIAN TRACK (IT) =================
        Lesson(
            id = "it_a1_01",
            level = AcademicLevel.A1,
            trackCode = "it",
            orderNumber = 1,
            titleAr = "سحر اللغة الإيطالية والتحيات اليومية",
            titleForeign = "Ciao Italia: Saluti & Vita Quotidiana",
            estimatedMinutes = 10,
            lectureConceptAr = "مدخل رائع للغة الإيطالية وموسيقيتها، كيف تلقي التحية وتطلب القهوة وتتفاعل في الشارع الإيطالي بكل عفوية.",
            grammarRule = GrammarRule(
                titleAr = "قاعدة أدوات التعريف والمذكر والمؤنث (Il, La, Un, Una)",
                explanationAr = "الكلمات المنتهية بـ o تكون مذكر عادة (il ragazzo)، والمنتهية بـ a تكون مؤنث (la ragazza).",
                formulaAr = "Il / La + Nome",
                practicalExampleForeign = "Un caffè espresso per favore, grazie mille!",
                practicalExampleArabic = "فنجان قهوة إسبريسو من فضلك، شكراً جزيلاً!"
            ),
            vocabulary = listOf(
                VocabWord(
                    id = "v_it_01",
                    foreignWord = "Piacere di conoscerti",
                    arabicMeaning = "سعيد جداً بلقائك / فرصة سعيدة",
                    arabicPhonetics = "بياتشيري دي كونوشيرتي",
                    exampleSentence = "Molto piacere di conoscerti, benvenuto a Roma!",
                    exampleTranslation = "فرصة سعيدة جداً بمعرفتك، أهلاً بك في روما!"
                ),
                VocabWord(
                    id = "v_it_02",
                    foreignWord = "Per favore / Per cortesia",
                    arabicMeaning = "من فضلك / لو سمحت",
                    arabicPhonetics = "بير فافوري / بير كورتيزيا",
                    exampleSentence = "Un conto per favore.",
                    exampleTranslation = "الحساب من فضلك."
                )
            ),
            conversationDialogue = listOf(
                DialogueLine(
                    speakerName = "ماركو من روما",
                    isUser = false,
                    foreignText = "Buongiorno! Sei per la prima volta in Italia?",
                    arabicTranslation = "صباح الخير! هل هذه مرتك الأولى في إيطاليا؟",
                    arabicPhonetics = "بون جورنو! سي بير لا بريما فولتا إن إيطاليا؟"
                ),
                DialogueLine(
                    speakerName = "أنت (الطالب)",
                    isUser = true,
                    foreignText = "Buongiorno Marco! Sì, amo la lingua e la cultura italiana.",
                    arabicTranslation = "صباح الخير ماركو! نعم، وأنا أعشق اللغة والثقافة الإيطالية.",
                    arabicPhonetics = "بون جورنو ماركو! سي، آمو لا لينغوا إيه لا كولتورا إيطاليانا."
                )
            ),
            quizQuestions = listOf(
                QuizQuestion(
                    id = "q_it_a1_01",
                    promptAr = "كيف تقول 'شكراً جزيلاً' بالإيطالية؟",
                    promptForeign = "Thank you very much",
                    options = listOf("Grazie mille", "Prego", "Arrivederci", "Buona notte"),
                    correctIndex = 0,
                    explanationAr = "'Grazie mille' تعني حرفياً ألف شكر، وهي التعبير الشائع والمحبوب في كل إيطاليا."
                )
            )
        )
    )

    val LAB_SCENARIOS: List<InteractiveLabScenario> = listOf(
        InteractiveLabScenario(
            id = "lab_airport",
            titleAr = "في المطار والجوازات والجمارك",
            descriptionAr = "محاكاة كاملة للتحدث مع ضابط الجوازات وموظف الأمتعة في مطارات أوروبا وأمريكا دون ارتباك.",
            emoji = "✈️",
            contextEnvironment = "Terminal 2 - Border Control Desk",
            dialogueRounds = listOf(
                DialogueLine(
                    speakerName = "Border Officer",
                    isUser = false,
                    foreignText = "Good morning. May I please see your passport and return flight ticket?",
                    arabicTranslation = "صباح الخير. هل يمكنني رؤية جواز سفرك وتذكرة رحلة العودة من فضلك؟",
                    arabicPhonetics = "غود مورنينغ. ماي آي بليز سي يور باسبورت أند ريتيرن فلايت تيكت؟"
                ),
                DialogueLine(
                    speakerName = "You (Traveler)",
                    isUser = true,
                    foreignText = "Good morning officer. Here is my passport and my hotel booking confirmation.",
                    arabicTranslation = "صباح الخير يا سيدي. تفضل جواز سفري وتأكيد حجز الفندق.",
                    arabicPhonetics = "غود مورنينغ أوفيسر. هير إز ماي باسبورت أند ماي هوتيل بوكينغ كونفيرميشن."
                ),
                DialogueLine(
                    speakerName = "Border Officer",
                    isUser = false,
                    foreignText = "What is the purpose of your stay and how long will you be visiting?",
                    arabicTranslation = "ما هو الغرض من إقامتك وكم من الوقت ستستغرق زيارتك؟",
                    arabicPhonetics = "وات إز ذا بيربوس أوف يور ستاي أند هاو لونغ ويل يو بي فيزيتينغ؟"
                ),
                DialogueLine(
                    speakerName = "You (Traveler)",
                    isUser = true,
                    foreignText = "I am visiting for university studies and cultural tourism for two weeks.",
                    arabicTranslation = "أنا قادم للدراسة الأكاديمية والسياحة الثقافية لمدة أسبوعين.",
                    arabicPhonetics = "آي آم فيزيتينغ فور يونيفرسيتي ستاديز أند كالتشرال توریزم فور تو ويكس."
                )
            ),
            keyUsefulExpressions = listOf(
                VocabWord("exp_1", "Purpose of visit", "الغرض من الزيارة", "بيربوس أوف فيزيت", "My purpose of visit is education.", "غرض زيارتي هو التعليم."),
                VocabWord("exp_2", "Nothing to declare", "ليس لدي ما أصرح عنه للجمارك", "ناثينغ تو ديكلير", "I have nothing to declare.", "لا أحمل أي شيء خاضع للجمارك.")
            )
        ),
        InteractiveLabScenario(
            id = "lab_job_interview",
            titleAr = "مقابلة العمل الرسمية والتقديم للوظيفة",
            descriptionAr = "محاكاة واقعية لأسئلة مقابلات العمل الدولية، والحديث عن نقاط القوة والخبرات والتفاوض على الراتب.",
            emoji = "💼",
            contextEnvironment = "Executive Boardroom - Global Company",
            dialogueRounds = listOf(
                DialogueLine(
                    speakerName = "Interviewer",
                    isUser = false,
                    foreignText = "Welcome! Could you highlight your key strengths and why you're a great fit for our team?",
                    arabicTranslation = "أهلاً بك! هل يمكنك إبراز أهم نقاط قوتك ولماذا تعتبر إضافة ممتازة لفريقنا؟",
                    arabicPhonetics = "ويلكم! كود يو هايلايت يور كي سترينغثس؟"
                ),
                DialogueLine(
                    speakerName = "You (Candidate)",
                    isUser = true,
                    foreignText = "Certainly. I excel at problem solving, multilingual communication, and delivering projects under deadlines.",
                    arabicTranslation = "بكل تأكيد. أتميز بحل المشكلات والتواصل بعدة لغات وإنجاز المشروعات في مواعيدها المحددة.",
                    arabicPhonetics = "سيرتنلي. آي إكسل أت بروبلم سولفينغ، مالتي-لينغوال كوميونيكيشن."
                )
            ),
            keyUsefulExpressions = listOf(
                VocabWord("exp_3", "Key strength", "نقطة قوة أساسية", "كي سترينغث", "My key strength is adaptability.", "نقطة قوتي الرئيسية هي سرعة التكيف.")
            )
        ),
        InteractiveLabScenario(
            id = "lab_hotel_checkin",
            titleAr = "حجز الفندق والاستقبال وحل المشكلات",
            descriptionAr = "تعلم طلب غرفة هادئة، الاستفسار عن الإنترنت، وحل أي ملاحظة حول الغرفة بكل احترام.",
            emoji = "🏨",
            contextEnvironment = "Grand Palace Hotel Reception",
            dialogueRounds = listOf(
                DialogueLine(
                    speakerName = "Receptionist",
                    isUser = false,
                    foreignText = "Welcome to our hotel! Do you have a reservation with us?",
                    arabicTranslation = "أهلاً بك في فندقنا! هل لديك حجز مسبق لدينا؟",
                    arabicPhonetics = "ويلكم تو آور هوتيل! دو يو هاف أ ريزيرفيشن؟"
                ),
                DialogueLine(
                    speakerName = "You (Guest)",
                    isUser = true,
                    foreignText = "Yes, I have a reservation under my name for three nights, preferably on a quiet upper floor.",
                    arabicTranslation = "نعم، لدي حجز باسمي لمدة 3 ليالٍ، وأفضل أن تكون الغرفة في طابق علوي هادئ.",
                    arabicPhonetics = "يَس، آي هاف أ ريزيرفيشن أندر ماي نيم فور ثري نايتس."
                )
            ),
            keyUsefulExpressions = listOf(
                VocabWord("exp_4", "Quiet upper floor", "طابق علوي هادئ", "كوايت أَبَر فلور", "Could I have a room on a quiet upper floor?", "هل يمكنني الحصول على غرفة في طابق علوي هادئ؟")
            )
        )
    )
}
