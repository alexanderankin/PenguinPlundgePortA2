package com.twopercent.render;

import com.twopercent.main.ActionZone;
import com.twopercent.main.InputController;

import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Button extends VisibleObject {
	private String text, id;
	private int buttonKey = 0, buttonHoverKey = 1, buttonSpriteKey;
	private static int state;
	private Text textView;

	public Button(int x, int y, int width, int height, String text, int buttonSpriteKey, String id) {
		super(new Group());
		this.text = text;
		this.buttonSpriteKey = buttonSpriteKey;
		this.id = id;
		setX(x);
		setY(y);
		setWidth(width);
		setHeight(height);

		state = 0;

		setImageViewToImage(new Image(Button.class.getResource("/res/images/buttonA1.png").toString()));
		getImageView().setViewport(
				new Rectangle2D(getWidth() * buttonKey, getHeight() * buttonSpriteKey, getWidth(), getHeight()));
		getImageView().setX(getX());
		getImageView().setY(getY());
		getGroup().getChildren().add(getImageView());

		textView = new Text();
		textView.setFont(new Font("Arial", 42));
		textView.setFill(new Color(1, 1, 1, 1));
		DropShadow dropShadow = new DropShadow();
		dropShadow.setRadius(5);
		dropShadow.setOffsetX(2);
		dropShadow.setOffsetY(2);
		dropShadow.setColor(new Color(0, 0, 0, 1));
		textView.setEffect(dropShadow);

		textView.setText(text);

		textView.setX(getX() + (getWidth() / 2) - (textView.getLayoutBounds().getWidth() / 2));
		textView.setY(getY() + 47);

		setType("button");

		addActionListener();

		getGroup().getChildren().add(textView);

	}

	public Button(int x, int y, int width, int height, String id) {
		// this button will NOT be viewable, however visible always returns TRUE
		super(new Group());
		setX(x);
		setY(y);
		setWidth(width);
		setHeight(height);
		this.id = id;
		setType("nullButton");

		state = 0;

		addActionListener();

	}

	public void addActionListener() {
		InputController.addActionZone((int) getX(), (int) getY(), getWidth(), getHeight(), id);

	}

	public void update() {
		if (getImageView() != null) {
			if (getImageView().isVisible()) {
				textView.setVisible(true);
			} else if (!getImageView().isVisible()) {
				textView.setVisible(false);
			}
		}
	}

	public int getButtonKey() {
		return buttonKey;
	}

	public void setButtonKey(int buttonKey) {
		this.buttonKey = buttonKey;
	}

	public int getButtonHoverKey() {
		return buttonHoverKey;
	}

	public void setButtonHoverKey(int buttonHoverKey) {
		this.buttonHoverKey = buttonHoverKey;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
		if (getImageView() != null) {
			if (state == 0) {
				getImageView()
						.setViewport(
								new Rectangle2D(getWidth() * buttonKey, getHeight() * buttonSpriteKey, getWidth(),
										getHeight()));
			}
			if (state == 1) {
				getImageView()
						.setViewport(
								new Rectangle2D(getWidth() * buttonKey, getHeight() * buttonSpriteKey, getWidth(),
										getHeight()));
			}
			if (state == 2) {
				getImageView().setViewport(
						new Rectangle2D(getWidth() * buttonHoverKey, getHeight() * buttonSpriteKey, getWidth(),
								getHeight()));
			}
		}
	}

	public Text getTextView() {
		return textView;
	}

	public void setTextView(Text textView) {
		this.textView = textView;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}