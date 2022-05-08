package LibraryInfo

import android.app.Dialog
import android.content.Context
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.detail_dialog.*
import org.techtown.sns_project.R

class CustomDialog(context: Context) {
    private val dialog = Dialog(context)

    fun showDialog(name:String,add:String,tel:String,time:String,close:String,link:String) {
        dialog.setContentView(R.layout.detail_dialog)
        dialog.window!!.setLayout(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT)
        dialog.setCanceledOnTouchOutside(true)
        dialog.setCancelable(true)
        dialog.show()

        dialog.detailTextView.append("도서관명 : \n${name}\n")
        dialog.detailTextView.append("주소 : \n${add}\n")
        dialog.detailTextView.append("전화번호 : \n${tel}\n")
        dialog.detailTextView.append("운영시간 : \n${time}\n")
        dialog.detailTextView.append("휴무일 : \n${close}\n")
        dialog.detailTextView.append("도서관 홈페이지 : \n${link}")
    }
}