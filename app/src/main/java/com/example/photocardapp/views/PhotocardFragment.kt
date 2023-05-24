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
import com.example.photocardapp.R
import com.example.photocardapp.databinding.FragmentPhotocardBinding
import com.example.photocardapp.models.PhotocardModel
import com.example.photocardapp.views.EditNoteFragment

class PhotocardFragment : Fragment() {

    private var _binding: FragmentPhotocardBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPhotocardBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val photocard = arguments?.getParcelable<PhotocardModel>("photocard")
        if (photocard != null) {
            binding.apply {
                idolNameTextView.text = photocard.idolName
                titleTextView.text = photocard.title
                Glide.with(root)
                    .load(photocard.imageUrl)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .override(600, 800)  // Set the desired width and height
                    .centerCrop()        // Apply center crop transformation
                    .into(imageView)
                dateReceivedTextView.text = photocard.dateReceived
                notesTextView.text = photocard.notes

                // set up button
                buttonEditNote.setOnClickListener {
                    val fragment = EditNoteFragment()
                    fragment.arguments = arguments
                    parentFragmentManager.beginTransaction()
                        .replace(R.id.nav_host_fragment_content_main, fragment)
                        .addToBackStack(null)
                        .commit()
                }
            }
        }

    }
}