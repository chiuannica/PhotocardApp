import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.photocardapp.models.PhotocardModel
import com.example.photocardapp.repository.SharedPreferencesRepository
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
class PhotocardsViewModel(private val sharedPreferencesRepository: SharedPreferencesRepository) : ViewModel() {
    private val _photocardsLiveData = MutableLiveData<List<PhotocardModel>>()
    private val samplePhotocards = listOf(
        PhotocardModel(1,
            "Yuta",
            "\uD83E\uDD14 Photocard",
            LocalDateTime.now().minusYears(1).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")),
            "",
            "https://static.wikia.nocookie.net/kboy-group/images/e/ef/Yuta_Earthquake_%281%29.jpeg/revision/latest?cb=20211204185242"
        ),
        PhotocardModel(2,
            "Yuta",
            "Joker Photocard",
            LocalDateTime.now().minusYears(2).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")),
            "",
            "https://static.wikia.nocookie.net/smtown/images/b/b6/Yuta_%28Sticker%29_2.png/revision/latest?cb=20210825211222"
        ),
        PhotocardModel(3,
            "Yuta",
            "Light Photocard",
            LocalDateTime.now().minusMonths(4).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")),
            "",
            "https://www.allkpop.com/upload/2022/06/content/050026/web_data/allkpop_1654403973_yuta-universe-283-29.jpg"
        ),

        PhotocardModel(4,
            "Yuta",
            "Holding Leg Photocard",
            LocalDateTime.now().minusDays(78).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")),
            "",
            "https://qph.cf2.quoracdn.net/main-qimg-ff3339937bda1aeb68e2c39f23acffd6-lq"
        ),

        PhotocardModel(5,
            "Yuta",
            "Ouch My Neck Photocard",
            LocalDateTime.now().minusDays(782).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")),
            "",
            "https://ih1.redbubble.net/image.788829463.8617/flat,750x,075,f-pad,750x1000,f8f8f8.u3.jpg"
        ),
        PhotocardModel(
            6,
            "Yuta",
            "Pier Photocard",
            LocalDateTime.now().minusDays(238).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")),
            "",
            "https://pm1.aminoapps.com/7357/eb2aa71f3334298bff9b268de7f0bb76d6580c22r1-750-1126v2_hq.jpg"
        ),
        PhotocardModel(
            7,
            "Yuta",
            "Red Hair on Stage Photocard",
            LocalDateTime.now().minusDays(2382).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")),
            "",
            "https://pbs.twimg.com/media/FEKTRqmUYAcwkqF.jpg"
        ),
        PhotocardModel(
            8,
            "Yuta",
            "Ay-Yo Palm Tree Photocard",
            LocalDateTime.now().minusDays(22).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")),
            "",
            "https://static.wikia.nocookie.net/kpop/images/a/ae/NCT_127_Yuta_Ay-Yo_concept_photo_1.png/revision/latest?cb=20230221033202"
        ),
        PhotocardModel(
            9,
            "Yuta",
            "JungMaJaeYu Colombia Photocard",
            LocalDateTime.now().minusDays(22).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")),
            "",
            "https://preview.redd.it/230124-nct-127-twitter-update-with-jungwoo-mark-jaehyun-and-v0-synygjkad4ea1.jpg?auto=webp&s=b484a9c0bc9a17d260c2c49b2e09f32064c3a885"
        ),
        PhotocardModel(
            10,
            "Yuta",
            "Blonde Photocard",
            LocalDateTime.now().minusDays(223).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")),
            "",
            "https://64.media.tumblr.com/9caa9aa29b4be3529749c3819643b1cb/tumblr_po3gx7Wd7E1vm58pa_1280.jpg"
        ),
        PhotocardModel(
            11,
            "Yuta",
            "Men's Non-No Photocard",
            LocalDateTime.now().minusDays(212).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")),
            "",
            "https://www.mensnonno.jp/wp-content/uploads/2022/12/2022_12_nct_sm_02.jpg"
        ),
        PhotocardModel(
            12,
            "Yuta",
            "Cherry Bomb Gif Photocard",
            LocalDateTime.now().minusDays(322).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")),
            "",
            "https://64.media.tumblr.com/c096656e9282030b93c8a820f00ae51d/fb8597a23f02de08-d6/s400x600/a48920660842e06799545b8b6f8b9a5496b16dde.gif"
        ),
        PhotocardModel(
            13,
            "Yuta",
            "Shotaro and Yuta Photocard",
            LocalDateTime.now().minusDays(22).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")),
            "",
            "https://kpopping.com/documents/cc/1/800/NCT-YUTA-x-SHOTARO-for-MEN-S-NON-NO-Japan-June-Issue-2022-documents-2.jpeg?v=c2365"
        ),
        PhotocardModel(
            14,
            "Yuta",
            "Samurai Photocard",
            LocalDateTime.now().minusDays(92).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")),
            "",
            "https://pbs.twimg.com/media/FDAbQ95acAAGrIL?format=jpg&name=4096x4096"
        ),
        PhotocardModel(
            15,
            "Yuta",
            "Predebut Yuta and Ten Photocard",
            LocalDateTime.now().minusDays(2).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")),
            "",
            "https://i.pinimg.com/originals/16/bd/15/16bd15258ca30a5c8c9f41d0d4632b1a.jpg"
        ),
        PhotocardModel(
            16,
            "Yuta",
            "Yellow Photocard",
            LocalDateTime.now().minusDays(222).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")),
            "",
            "https://preview.redd.it/220224-yuta-for-num%C3%A9ro-tokyo-april-2022-issue-v0-qhwwoglxzoj81.jpg?width=640&crop=smart&auto=webp&s=90d18b28f8cf5d1523d69eb8a0b1cc8b8ff2a632"
        ),
        PhotocardModel(
            17,
            "Yuta",
            "UwU Photocard",
            LocalDateTime.now().minusDays(8922).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")),
            "",
            "https://i.pinimg.com/474x/55/07/d3/5507d3382e52bc5947abf7999076ee75.jpg"
        ),
        PhotocardModel(
            18,
            "Yuta",
            "Staring Contest Photocard",
            LocalDateTime.now().minusDays(92).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")),
            "",
            "https://i.pinimg.com/736x/ca/3d/7d/ca3d7d077e7665ceca9ef62b8d992e20.jpg"
        ),
        PhotocardModel(
            19,
            "Yuta",
            "Forbes Photocard",
            LocalDateTime.now().minusDays(90).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")),
            "",
            "https://preview.redd.it/6pzg7k0r6mj91.jpg?width=640&crop=smart&auto=webp&s=efe7d01f728884cbf17292497dd3d796cb374e8e"
        ),
        PhotocardModel(
            20,
            "Yuta",
            "Natural Photocard",
            LocalDateTime.now().minusDays(245).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")),
            "",
            "https://i.pinimg.com/originals/7d/b6/b4/7db6b42bee778ff9ba41004ec221f155.jpg"
        )
    )

    init {
        loadPhotocards()
    }
    val photocardsLiveData: MutableLiveData<List<PhotocardModel>>
        get() = _photocardsLiveData

    fun getRandomPhotocard(): PhotocardModel {
        return samplePhotocards[(0..samplePhotocards.size).random()]
    }

    fun addNewPhotocard() {
        val newPhotocard = getRandomPhotocard()
        newPhotocard.dateReceived = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))
        val currentPhotocards = _photocardsLiveData.value?.toMutableList() ?: mutableListOf()
        currentPhotocards.add(newPhotocard)
        _photocardsLiveData.value = currentPhotocards
        savePhotocards()
    }

    fun savePhotocards() {
        photocardsLiveData.value?.let {
            sharedPreferencesRepository.savePhotocards(it)
        }
    }

    fun deleteAllPhotocards() {
        photocardsLiveData.value = listOf()
    }

    fun giveAllPhotocards() {
        photocardsLiveData.value = samplePhotocards
    }

    fun saveNotesPhotocard(newPhotocard: PhotocardModel) {
        photocardsLiveData.value?.let {
            sharedPreferencesRepository.saveNotesPhotocard(newPhotocard,
                it
            )
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun loadPhotocards() {
        var photocards = sharedPreferencesRepository.loadPhotocards()
        if (photocards == null) {
            photocards = getSamplePhotocards()
        }
        // Retrieve photocards from a data source (e.g., database, API)
        photocardsLiveData.value = photocards!!
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun getSamplePhotocards(): List<PhotocardModel> {
        return samplePhotocards
    }

    fun getPhotocardById(id: Int): PhotocardModel {
        return samplePhotocards[id]
    }
}
