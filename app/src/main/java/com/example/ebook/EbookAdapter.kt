import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.ebook.EbookItemCallback
import com.example.ebook.EbookViewHolder
import com.example.ebook.Items
import com.example.ebook.R

class EbookAdapter (var ebooks: MutableList<Items>, var ebookItemCallback: EbookItemCallback): Adapter<EbookViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EbookViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view: View = inflater.inflate(R.layout.list_book, parent, false)
        return EbookViewHolder(view)
    }

    override fun getItemCount(): Int {
        return ebooks.size
    }

    override fun onBindViewHolder(holder: EbookViewHolder, position: Int) {
        holder.bind(ebooks[position], ebookItemCallback)

    }
}
