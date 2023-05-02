package com.example.cs003b_finalproject;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import android.content.Context;


public class BuildingView extends View
{
    final private String _buildingName;
    final private Paint _rectPaint;
    final private Paint _textPaint;
    final private Rect _textBounds;

    public BuildingView(Context context)
    {
        this(context, null);
    }

    public BuildingView(Context context, AttributeSet attributeSet)
    {
        super(context, attributeSet);

        // Get the attributes which come from the attributeSet and associated
        // with the BuildingView declared in values/attrs.xml
        TypedArray attributes = context.obtainStyledAttributes(attributeSet, R.styleable.BuildingView);

        // Grab expected attributes.
        this._buildingName = attributes.getString(R.styleable.BuildingView_buildingName);
        int _buildingColor = attributes.getColor(R.styleable.BuildingView_buildingColor, Color.WHITE);
        int _textColor = attributes.getColor(R.styleable.BuildingView_textColor, Color.BLACK);
        float _textSize = attributes.getFloat(R.styleable.BuildingView_textSize, 30.f);
        float _rotationAngle = attributes.getFloat(R.styleable.BuildingView_rotation, 0.f);


        // Initializing Paint objects.
        this._rectPaint = new Paint();
        this._rectPaint.setColor(_buildingColor);

        this._textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        this._textPaint.setTextAlign(Paint.Align.CENTER);
        this._textPaint.setColor(_textColor);
        this._textPaint.setTextSize(_textSize);

        // Initializing Rect
        this._textBounds = new Rect();

        // Recycle the TypedArray to avoid memory leaks.
        attributes.recycle();
        setRotation(_rotationAngle);
    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);
        // Draw the background rectangle
        canvas.drawRect(getTranslationX(), getTranslationY(), getTranslationX() + getWidth(), getTranslationY() + getHeight(), _rectPaint);
        // Draw the text (building name)
        if (this._buildingName != null)
        {
            float centerX = getWidth() / 2.f;
            float centerY = getHeight() / 2.f + this._textBounds.height() / 2.f;
            canvas.drawText(this._buildingName, centerX, centerY, this._textPaint);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
    {
        int textWidth = (int) this._textPaint.measureText(this._buildingName);
        this._textPaint.getTextBounds(this._buildingName, 0, this._buildingName.length(), this._textBounds);
        int textHeight = this._textBounds.height();

        int paddingX = getPaddingLeft() + getPaddingRight();
        int paddingY = getPaddingTop() + getPaddingBottom();
        setMeasuredDimension(textWidth + paddingX, textHeight + paddingY);
    }

    public String getName()
    {
        return this._buildingName;
    }
}