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
    init {
        loadPhotocards()
    }
    val photocardsLiveData: MutableLiveData<List<PhotocardModel>>
        get() = _photocardsLiveData

    @RequiresApi(Build.VERSION_CODES.O)
    private fun getRandomPhotocard(): PhotocardModel {
        var photocards = getSamplePhotocards()
        return photocards[(0..photocards.size).random()]
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun addNewPhotocard() {
        val newPhotocard = getRandomPhotocard()
        newPhotocard.dateReceived = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))
        val currentPhotocards = _photocardsLiveData.value?.toMutableList() ?: mutableListOf()
        currentPhotocards.add(newPhotocard)
        _photocardsLiveData.value = currentPhotocards
        savePhotocards()
    }

    fun savePhotocards() {
        photocardsLiveData.value?.let { sharedPreferencesRepository.savePhotocards(it) }
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
        return (listOf(
            PhotocardModel(1,
                "Yuta",
                "First Photocard",
                LocalDateTime.now().minusYears(1).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")),
                "Yuta is awesome!",
                "https://static.wikia.nocookie.net/kboy-group/images/e/ef/Yuta_Earthquake_%281%29.jpeg/revision/latest?cb=20211204185242"
            ),
            PhotocardModel(2,
                "Yuta",
                "Second Photocard",
                LocalDateTime.now().minusYears(2).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")),
                "Yuta is awesome again!",
                "https://static.wikia.nocookie.net/smtown/images/b/b6/Yuta_%28Sticker%29_2.png/revision/latest?cb=20210825211222"
            ),
            PhotocardModel(3,
                "Yuta",
                "Third Photocard",
                LocalDateTime.now().minusMonths(4).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")),
                "Yuta is awesome again!",
                "https://www.allkpop.com/upload/2022/06/content/050026/web_data/allkpop_1654403973_yuta-universe-283-29.jpg"
            ),

            PhotocardModel(4,
                "Yuta",
                "Fourth Photocard",
                LocalDateTime.now().minusDays(78).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")),
                "Yuta is awesome again!",
                "https://qph.cf2.quoracdn.net/main-qimg-ff3339937bda1aeb68e2c39f23acffd6-lq"
            ),

            PhotocardModel(5,
                "Yuta",
                "Fifth Photocard",
                LocalDateTime.now().minusDays(782).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")),
                "Yuta is awesome again!",
                "https://ih1.redbubble.net/image.788829463.8617/flat,750x,075,f-pad,750x1000,f8f8f8.u3.jpg"
            ),
            PhotocardModel(
                6,
                "Yuta",
                "First Photocard",
                LocalDateTime.now().minusDays(238).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")),
                "Yuta's first photocard -- he is so cute!",
                "https://static.wikia.nocookie.net/kpop/images/a/ae/NCT_127_Yuta_Ay-Yo_concept_photo_1.png/revision/latest?cb=20230221033202"
            ),
            PhotocardModel(
                7,
                "Yuta",
                "Second Photocard",
                LocalDateTime.now().minusDays(2382).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")),
                "Yuta's second photocard -- he is so cute!",
                "https://static.wikia.nocookie.net/kpop/images/a/ae/NCT_127_Yuta_Ay-Yo_concept_photo_1.png/revision/latest?cb=20230221033202"
            ),
            PhotocardModel(
                8,
                "Yuta",
                "Third Photocard",
                LocalDateTime.now().minusDays(22).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")),
                "Yuta's third photocard -- he is so cute!",
                "https://static.wikia.nocookie.net/kpop/images/a/ae/NCT_127_Yuta_Ay-Yo_concept_photo_1.png/revision/latest?cb=20230221033202"
            )

        ))
    }
}
