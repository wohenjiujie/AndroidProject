package com.example.junior

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class MainActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btn_px = findViewById(R.id.btn_px)
        val btn_color = findViewById(R.id.btn_color) as Button
        val btn_screen = findViewById(R.id.btn_screen) as Button
        btn_px.setOnClickListener(this)
        btn_color.setOnClickListener(this)
        btn_screen.setOnClickListener(this)

        val btn_margin = findViewById(R.id.btn_margin) as Button
        val btn_gravity = findViewById(R.id.btn_gravity) as Button
        val btn_scroll = findViewById(R.id.btn_scroll)
        btn_margin.setOnClickListener(this)
        btn_gravity.setOnClickListener(this)
        btn_scroll.setOnClickListener(this)

        val btn_marquee = findViewById(R.id.btn_marquee) as Button
        val btn_bbs = findViewById(R.id.btn_bbs) as Button
        val btn_click = findViewById(R.id.btn_click) as Button
        btn_marquee.setOnClickListener(this)
        btn_bbs.setOnClickListener(this)
        btn_click.setOnClickListener(this)

        val btn_scale = findViewById(R.id.btn_scale) as Button
        val btn_capture = findViewById(R.id.btn_capture) as Button
        val btn_icon = findViewById(R.id.btn_icon) as Button
        btn_scale.setOnClickListener(this)
        btn_capture.setOnClickListener(this)
        btn_icon.setOnClickListener(this)

        val btn_state = findViewById(R.id.btn_state) as Button
        val btn_shape = findViewById(R.id.btn_shape) as Button
        val btn_nine = findViewById(R.id.btn_nine) as Button
        btn_state.setOnClickListener(this)
        btn_shape.setOnClickListener(this)
        btn_nine.setOnClickListener(this)

        val btn_calculator = findViewById(R.id.btn_calculator) as Button
        btn_calculator.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        if (v.id == R.id.btn_px) {
            val intent = Intent(this, PxActivity::class.java)
            startActivity(intent)
        } else if (v.id == R.id.btn_color) {
            val intent = Intent(this, ColorActivity::class.java)
            startActivity(intent)
        } else if (v.id == R.id.btn_screen) {
            val intent = Intent(this, ScreenActivity::class.java)
            startActivity(intent)
        } else if (v.id == R.id.btn_margin) {
            val intent = Intent(this, MarginActivity::class.java)
            startActivity(intent)
        } else if (v.id == R.id.btn_gravity) {
            val intent = Intent(this, GravityActivity::class.java)
            startActivity(intent)
        } else if (v.id == R.id.btn_scroll) {
            val intent = Intent(this, ScrollActivity::class.java)
            startActivity(intent)
        } else if (v.id == R.id.btn_marquee) {
            val intent = Intent(this, MarqueeActivity::class.java)
            startActivity(intent)
        } else if (v.id == R.id.btn_bbs) {
            val intent = Intent(this, BbsActivity::class.java)
            startActivity(intent)
        } else if (v.id == R.id.btn_click) {
            val intent = Intent(this, ClickActivity::class.java)
            startActivity(intent)
        } else if (v.id == R.id.btn_scale) {
            val intent = Intent(this, ScaleActivity::class.java)
            startActivity(intent)
        } else if (v.id == R.id.btn_capture) {
            val intent = Intent(this, CaptureActivity::class.java)
            startActivity(intent)
        } else if (v.id == R.id.btn_icon) {
            val intent = Intent(this, IconActivity::class.java)
            startActivity(intent)
        } else if (v.id == R.id.btn_state) {
            val intent = Intent(this, StateActivity::class.java)
            startActivity(intent)
        } else if (v.id == R.id.btn_shape) {
            val intent = Intent(this, ShapeActivity::class.java)
            startActivity(intent)
        } else if (v.id == R.id.btn_nine) {
            val intent = Intent(this, NineActivity::class.java)
            startActivity(intent)
        } else if (v.id == R.id.btn_calculator) {
            val intent = Intent(this, CalculatorActivity::class.java)
            startActivity(intent)
        }
    }

}
