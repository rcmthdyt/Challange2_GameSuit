package id.rcmthdyt.challange2

import android.graphics.Color
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import id.rcmthdyt.challange2.databinding.ActivityMainBinding


class   MainActivity : AppCompatActivity() {

    private val textTitle = (R.string.app_title_text)
    private val pilihanSuit = arrayOf("Batu", "Gunting", "Kertas")
    private var status: String? = null
    private var pemain1: String? = null
    private var pemain2: String? = null
    private var _binding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(_binding?.root)

        _binding?.textTitle?.text = (getText(textTitle))
        _binding?.textTerminal?.text = "VS"

        // 0: Batu      1: Gunting      2:Kertas
        _binding?.playerBtnBatu?.setOnClickListener {
            refreshPick()
            activePickBatu()
            pemain1 = pilihanSuit[0]
            Log.d("Pemain 1 Input", "$pemain1")
            doRule()
        }
        _binding?.playerBtnGunting?.setOnClickListener {
            refreshPick()
            activePickGunting()
            pemain1 = pilihanSuit[1]
            Log.d("Pemain 1 Input", "$pemain1")
            doRule()
        }
        _binding?.playerBtnKertas?.setOnClickListener {
            refreshPick()
            activePickKertas()
            pemain1 = pilihanSuit[2]
            Log.d("Pemain 1 Input", "$pemain1")
            doRule()
        }
        _binding?.btnLockPick?.setOnClickListener {
            playTerminal()
            disablePick()
        }
        _binding?.btnRefresh?.setOnClickListener {
            enablePick()
            refreshPick()
            _binding?.textTerminal?.text = "VS"
        }
    }

    private fun doRule() {
        pemain2 = pilihanSuit.random()
        // 0: Batu      1: Gunting      2:Kertas
        status = if (
            pemain1 == pilihanSuit[0] && pemain2 == pilihanSuit[1] ||
            pemain1 == pilihanSuit[1] && pemain2 == pilihanSuit[2] ||
            pemain1 == pilihanSuit[2] && pemain2 == pilihanSuit[0]
        ) {
            "Pemain 1 MENANG!"
        } else if (
            pemain1 == pilihanSuit[0] && pemain2 == pilihanSuit[2] ||
            pemain1 == pilihanSuit[1] && pemain2 == pilihanSuit[0] ||
            pemain1 == pilihanSuit[2] && pemain2 == pilihanSuit[1]
        ) {
            "COM MENANG!"
        } else {
            "DRAW!"
        }
    }

    private fun activePickBatu() {
        _binding?.playerBtnBatu?.setBackgroundColor(resources.getColor(R.color.active))
        _binding?.playerBtnGunting?.setColorFilter(Color.LTGRAY)
        _binding?.playerBtnKertas?.setColorFilter(Color.LTGRAY)
        _binding?.btnLockPick?.isVisible = true
        _binding?.btnLockPick?.text = "Kunci Pilihan\nBatu"
    }

    private fun activePickGunting() {
        _binding?.playerBtnBatu?.setColorFilter(Color.LTGRAY)
        _binding?.playerBtnGunting?.setBackgroundColor(resources.getColor(R.color.active))
        _binding?.playerBtnKertas?.setColorFilter(Color.LTGRAY)
        _binding?.btnLockPick?.isVisible = true
        _binding?.btnLockPick?.text = "Kunci Pilihan\nGunting"
    }

    private fun activePickKertas() {
        _binding?.playerBtnBatu?.setColorFilter(Color.LTGRAY)
        _binding?.playerBtnGunting?.setColorFilter(Color.LTGRAY)
        _binding?.playerBtnKertas?.setBackgroundColor(resources.getColor(R.color.active))
        _binding?.btnLockPick?.isVisible = true
        _binding?.btnLockPick?.text = "Kunci Pilihan\nKertas"
    }

    private fun inactivePickCom() {
        _binding?.comBtnBatu?.setColorFilter(Color.LTGRAY)
        _binding?.comBtnGunting?.setColorFilter(Color.LTGRAY)
        _binding?.comBtnKertas?.setColorFilter(Color.LTGRAY)
    }

    private fun refreshPick() {
        _binding?.btnLockPick?.isVisible = false
        _binding?.playerBtnBatu?.setBackgroundColor(Color.TRANSPARENT)
        _binding?.playerBtnBatu?.colorFilter = null
        _binding?.comBtnBatu?.setBackgroundColor(Color.TRANSPARENT)
        _binding?.comBtnBatu?.colorFilter = null
        _binding?.playerBtnGunting?.setBackgroundColor(Color.TRANSPARENT)
        _binding?.playerBtnGunting?.colorFilter = null
        _binding?.comBtnGunting?.setBackgroundColor(Color.TRANSPARENT)
        _binding?.comBtnGunting?.colorFilter = null
        _binding?.playerBtnKertas?.setBackgroundColor(Color.TRANSPARENT)
        _binding?.playerBtnKertas?.colorFilter = null
        _binding?.comBtnKertas?.setBackgroundColor(Color.TRANSPARENT)
        _binding?.comBtnKertas?.colorFilter = null
        _binding?.textTerminal?.setTextColor(Color.BLACK)
    }

    private fun disablePick() {
        _binding?.playerBtnBatu?.isClickable = false
        _binding?.playerBtnGunting?.isClickable = false
        _binding?.playerBtnKertas?.isClickable = false
        _binding?.btnLockPick?.isVisible = false
    }

    private fun enablePick() {
        _binding?.playerBtnBatu?.isClickable = true
        _binding?.playerBtnGunting?.isClickable = true
        _binding?.playerBtnKertas?.isClickable = true
    }

    fun comPickReveal() {
        // 0: Batu      1: Gunting      2:Kertas
        when (pemain2) {
            pilihanSuit[0] -> {
                _binding?.comBtnBatu?.setBackgroundColor(resources.getColor(R.color.active))
                _binding?.comBtnBatu?.colorFilter = null
            }
            pilihanSuit[1] -> {
                _binding?.comBtnGunting?.setBackgroundColor(resources.getColor(R.color.active))
                _binding?.comBtnGunting?.colorFilter = null
            }
            else -> {
                _binding?.comBtnKertas?.setBackgroundColor(resources.getColor(R.color.active))
                _binding?.comBtnKertas?.colorFilter = null
            }
        }
    }

    fun terminalText() {
        when (status) {
            "Pemain 1 MENANG!" -> {
                _binding?.textTerminal?.setTextColor(resources.getColor(R.color.player_win))
            }
            "COM MENANG!" -> {
                _binding?.textTerminal?.setTextColor(resources.getColor(R.color.com_win))
            }
            else -> {
                _binding?.textTerminal?.setTextColor(resources.getColor(R.color.draw))
            }
        }
    }

    private fun playTerminal() {
        inactivePickCom()
        val count: CountDownTimer = object : CountDownTimer(3000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                _binding?.textTerminal?.text = ((millisUntilFinished / 1000) + 1).toString()
                Log.d("Countdown", "playTerminal: ${((millisUntilFinished / 1000)) + 1}")
                _binding?.btnRefresh?.isClickable = false
                _binding?.btnRefresh?.isVisible = false
            }

            override fun onFinish() {
                _binding?.textTerminal?.text = status
                comPickReveal()
                terminalText()
                Log.d("Pemain COM Input", "$pemain2")
                Log.d("Hasil pertandingan,", "$status")
                _binding?.btnRefresh?.isClickable = true
                _binding?.btnRefresh?.isVisible = true
            }
        }
        count.start()

    }
}

class ActivityMainBinding {

}
