import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.photocardapp.models.PhotocardModel

class PhotocardsViewModel : ViewModel() {
    private val _photocardsLiveData = MutableLiveData<List<PhotocardModel>>()

    fun photocardsLiveData(): MutableLiveData<List<PhotocardModel>> {
        return _photocardsLiveData
    }

    fun loadPhotocards() {
        // Retrieve photocards from a data source (e.g., database, API)
        val photocards = (listOf <PhotocardModel>(
            PhotocardModel(1,
                "Yuta",
                "First Photocard",
                "1233",
            "Yuta is awesome!",
            "https://static.wikia.nocookie.net/kboy-group/images/e/ef/Yuta_Earthquake_%281%29.jpeg/revision/latest?cb=20211204185242"
            ),
            PhotocardModel(2,
                "Yuta",
                "Second Photocard",
                "1233",
                "Yuta is awesome again!",
                "https://static.wikia.nocookie.net/smtown/images/b/b6/Yuta_%28Sticker%29_2.png/revision/latest?cb=20210825211222"
            ),
            PhotocardModel(3,
                "Yuta",
                "Third Photocard",
                "1233",
                "Yuta is awesome again!",
                "https://www.allkpop.com/upload/2022/06/content/050026/web_data/allkpop_1654403973_yuta-universe-283-29.jpg"
            ),

            PhotocardModel(4,
                "Yuta",
                "Fourth Photocard",
                "1233",
                "Yuta is awesome again!",
                "https://qph.cf2.quoracdn.net/main-qimg-ff3339937bda1aeb68e2c39f23acffd6-lq"
            ),

            PhotocardModel(5,
                "Yuta",
                "Fifth Photocard",
                "1233",
                "Yuta is awesome again!",
                "https://ih1.redbubble.net/image.788829463.8617/flat,750x,075,f-pad,750x1000,f8f8f8.u3.jpg"
            ),
            PhotocardModel(
                0,
                "Yuta",
                "First Photocard",
                "1223",
                "Yuta's first photocard -- he is so cute!",
                "https://static.wikia.nocookie.net/kpop/images/a/ae/NCT_127_Yuta_Ay-Yo_concept_photo_1.png/revision/latest?cb=20230221033202"
            ),
            PhotocardModel(
                1,
                "Yuta",
                "Second Photocard",
                "1223",
                "Yuta's second photocard -- he is so cute!",
                "https://static.wikia.nocookie.net/kpop/images/a/ae/NCT_127_Yuta_Ay-Yo_concept_photo_1.png/revision/latest?cb=20230221033202"
            ),
            PhotocardModel(
                2,
                "Yuta",
                "Third Photocard",
                "1223",
                "Yuta's third photocard -- he is so cute!",
                "https://static.wikia.nocookie.net/kpop/images/a/ae/NCT_127_Yuta_Ay-Yo_concept_photo_1.png/revision/latest?cb=20230221033202"
            )

        ))
        _photocardsLiveData.value = photocards
    }
}
