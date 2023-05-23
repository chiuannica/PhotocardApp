import android.os.Build
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.photocardapp.databinding.FragmentPhotocardBinding
import com.example.photocardapp.models.PhotocardModel

class PhotocardFragment : Fragment() {

    private var _binding: FragmentPhotocardBinding? = null

    private fun binding(): FragmentPhotocardBinding {
        return _binding!!
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPhotocardBinding.inflate(inflater, container, false)
        return binding().root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val photocard = arguments?.getParcelable<PhotocardModel>("photocard")
        if (photocard != null) {
            binding().apply {
                idolNameTextView.text = photocard.idolName
                titleTextView.text = photocard.title
                Glide.with(root)
                    .load(photocard.imageUrl)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .override(500, 800)  // Set the desired width and height
                    .centerCrop()        // Apply center crop transformation
                    .into(imageView)
            }
        }

    }
}