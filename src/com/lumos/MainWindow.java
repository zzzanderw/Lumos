package com.lumos;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by Zander Work on 3/6/2017.
 */
public class MainWindow extends Window {
	private Player player;
	private ArrayList<Character> characters;

	public MainWindow(int windowWidth, int windowHeight, int charWidth, int charHeight, int locationX, int locationY) {
		super("Lumos", windowWidth, windowHeight, charWidth, charHeight, locationX, locationY);
		super.setKeyListener(this);
		characters = new ArrayList<>();
	}

	private void updatePositions() {
		if (!characters.isEmpty()) {
			Iterator<Character> iterator = characters.iterator();
			while (iterator.hasNext()) {
				Character character = iterator.next();
				for (int i = 0; i < charWidth; i++) {
					for (int j = 0; j < charHeight; j++) {
						charFrame[i][j] = '.';
						if (i == character.getX() && j == character.getY()) {
							charFrame[i+character.getDx()][j+character.getDy()] = '@';
						}
					}
				}
				character.moveX();
				character.moveY();
			}
		}
	}

	protected void renderFrame() {
		updatePositions();
		super.renderFrame();
	}

	@Override
	public void initUI() {
		super.initUI();
		player = Game.getInstance().getPlayer();
		characters.add(player);
		renderFrame();
	}

	public void processInput(Key key) {
		super.processInput(key);
		switch (key) {
			case LEFT_ARROW:
				if (player.getX() != 0) {
					player.setDx(-1);
				}
				break;
			case RIGHT_ARROW:
				if (player.getX() != (charWidth - 1)) {
					player.setDx(1);
				}
				break;
			case UP_ARROW:
				if (player.getY() != 0) {
					player.setDy(-1);
				}
				break;
			case DOWN_ARROW:
				if (player.getY() != (charHeight - 1)) {
					player.setDy(1);
				}
				break;
			case INVALID:
				//intentionally empty case
				break;
		}
		updatePositions();
		renderFrame();
	}

	public void addPlayer(Player player) {
		this.player = player;
		characters.add(player);
	}

	public void addNPC(Character character) {
		characters.add(character);
	}
}
