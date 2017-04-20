package com.example.softwaretest;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.googlecode.openwnn.legacy.OnHandWritingRecognize;
import com.googlecode.openwnn.legacy.WnnWord;
import com.googlecode.openwnn.legacy.CLOUDSONG.CandidateView;
import com.googlecode.openwnn.legacy.CLOUDSONG.CloudKeyboardInputManager;
import com.googlecode.openwnn.legacy.CLOUDSONG.OnCandidateSelected;
import com.googlecode.openwnn.legacy.CLOUDSONG.OnPinyinQueryed;
import com.googlecode.openwnn.legacy.CLOUDSONG.PinyinQueryResult;
import com.googlecode.openwnn.legacy.handwritingboard.HandWritingBoardLayout;

@SuppressLint("NewApi")
public class InputViewActivity extends Activity implements OnCandidateSelected, OnHandWritingRecognize, OnPinyinQueryed, OnClickListener {

	GridLayout keyboardGrid;
	HandWritingBoardLayout handWritingBoard;
	TextView inputShow;
	Button KEY_A;
	Button KEY_B;
	Button KEY_C;
	Button KEY_D;
	Button KEY_E;
	Button KEY_F;
	Button KEY_G;
	Button KEY_H;
	Button KEY_I;
	Button KEY_J;
	Button KEY_K;
	Button KEY_L;
	Button KEY_M;
	Button KEY_N;
	Button KEY_O;
	Button KEY_P;
	Button KEY_Q;
	Button KEY_R;
	Button KEY_S;
	Button KEY_T;
	Button KEY_U;
	Button KEY_V;
	Button KEY_W;
	Button KEY_X;
	Button KEY_Y;
	Button KEY_Z;
	Button KEY_SPACE;
	LinearLayout container;
	RelativeLayout candidateContainer;
	Button mShowMore;
	Button KEY_DEL;
	Button btnSelectKeyboard;
	Button btnSelectHandWriting;
	Button btnCleanHandWriting;
	TextView PinYinInput;

	CandidateView mCandidateView;

	private StringBuilder currentInput = new StringBuilder();
	private CloudKeyboardInputManager ckManager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.input_view);

		keyboardGrid = (GridLayout) findViewById(R.id.keyboardGrid);
		KEY_A = (Button) findViewById(R.id.KEY_A);
		KEY_B = (Button) findViewById(R.id.KEY_B);
		KEY_C = (Button) findViewById(R.id.KEY_C);
		KEY_D = (Button) findViewById(R.id.KEY_D);
		KEY_E = (Button) findViewById(R.id.KEY_E);
		KEY_F = (Button) findViewById(R.id.KEY_F);
		KEY_G = (Button) findViewById(R.id.KEY_G);
		KEY_H = (Button) findViewById(R.id.KEY_H);
		KEY_I = (Button) findViewById(R.id.KEY_I);
		KEY_J = (Button) findViewById(R.id.KEY_J);
		KEY_K = (Button) findViewById(R.id.KEY_K);
		KEY_L = (Button) findViewById(R.id.KEY_L);
		KEY_M = (Button) findViewById(R.id.KEY_M);
		KEY_N = (Button) findViewById(R.id.KEY_N);
		KEY_O = (Button) findViewById(R.id.KEY_O);
		KEY_P = (Button) findViewById(R.id.KEY_P);
		KEY_Q = (Button) findViewById(R.id.KEY_Q);
		KEY_R = (Button) findViewById(R.id.KEY_R);
		KEY_S = (Button) findViewById(R.id.KEY_S);
		KEY_T = (Button) findViewById(R.id.KEY_T);
		KEY_U = (Button) findViewById(R.id.KEY_U);
		KEY_V = (Button) findViewById(R.id.KEY_V);
		KEY_W = (Button) findViewById(R.id.KEY_W);
		KEY_X = (Button) findViewById(R.id.KEY_X);
		KEY_Y = (Button) findViewById(R.id.KEY_Y);
		KEY_Z = (Button) findViewById(R.id.KEY_Z);
		KEY_SPACE = (Button) findViewById(R.id.KEY_SPACE);
		container = (LinearLayout) findViewById(R.id.container);
		candidateContainer = (RelativeLayout) findViewById(R.id.candidateContainer);
		handWritingBoard = (HandWritingBoardLayout) findViewById(R.id.handwrtingboard);
		mShowMore = (Button) findViewById(R.id.btn_showMore);
		KEY_DEL = (Button) findViewById(R.id.KEY_DEL);
		btnSelectHandWriting = (Button) findViewById(R.id.selecthandwriting);
		btnSelectKeyboard = (Button) findViewById(R.id.selectkeyboard);
		btnCleanHandWriting = (Button) findViewById(R.id.clean);
		PinYinInput = (TextView) findViewById(R.id.pinyinInput);
		inputShow = (TextView) findViewById(R.id.candidateselected);

		ckManager = new CloudKeyboardInputManager();
		ckManager.setOnPinyinQueryed(this);
		mCandidateView = new CandidateView(this);
		mCandidateView.setOnCandidateSelected(this);
		RelativeLayout.LayoutParams lp1 = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
		lp1.addRule(RelativeLayout.LEFT_OF, R.id.btn_showMore);

		lp1.width = ViewGroup.LayoutParams.MATCH_PARENT;
		candidateContainer.addView(mCandidateView, lp1);
		System.currentTimeMillis();
		keyboardGrid.setVisibility(View.VISIBLE);
		handWritingBoard.setVisibility(View.GONE);
		handWritingBoard.setOnHandWritingRecognize(this);

		btnSelectHandWriting.setOnClickListener(this);
		KEY_SPACE.setOnClickListener(this);
		btnSelectHandWriting.setOnClickListener(this);
		btnCleanHandWriting.setOnClickListener(this);
		KEY_DEL.setOnClickListener(this);
		btnCleanHandWriting.setOnClickListener(this);
		btnSelectKeyboard.setOnClickListener(this);
		KEY_A.setOnClickListener(this);
		KEY_B.setOnClickListener(this);
		KEY_C.setOnClickListener(this);

		KEY_D.setOnClickListener(this);
		KEY_E.setOnClickListener(this);
		KEY_F.setOnClickListener(this);

		KEY_G.setOnClickListener(this);
		KEY_H.setOnClickListener(this);
		KEY_I.setOnClickListener(this);

		KEY_J.setOnClickListener(this);
		KEY_K.setOnClickListener(this);
		KEY_L.setOnClickListener(this);

		KEY_M.setOnClickListener(this);
		KEY_N.setOnClickListener(this);
		KEY_O.setOnClickListener(this);

		KEY_P.setOnClickListener(this);
		KEY_Q.setOnClickListener(this);
		KEY_R.setOnClickListener(this);

		KEY_S.setOnClickListener(this);
		KEY_T.setOnClickListener(this);
		KEY_U.setOnClickListener(this);

		KEY_V.setOnClickListener(this);
		KEY_W.setOnClickListener(this);
		KEY_X.setOnClickListener(this);

		KEY_Y.setOnClickListener(this);
		KEY_Z.setOnClickListener(this);
	}

	private void processInput(char[] chars) {
		ckManager.processInput(chars);
	}

	private void clearCurrentInput() {
		currentInput.delete(0, currentInput.length());
	}

	@Override
	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.KEY_SPACE:
			inputTest();
			break;
		case R.id.KEY_A:
			processInputA();
			break;
		case R.id.KEY_B:
			processInputB();
			break;
		case R.id.KEY_C:
			processInputC();
			break;
		case R.id.KEY_D:
			processInputD();
			break;
		case R.id.KEY_E:
			processInputE();
			break;
		case R.id.KEY_F:
			processInputF();
			break;
		case R.id.KEY_G:
			processInputG();
			break;
		case R.id.KEY_H:
			processInputH();
			break;
		case R.id.KEY_I:
			processInputI();
			break;
		case R.id.KEY_J:
			processInputJ();
			break;
		case R.id.KEY_K:
			processInputK();
			break;
		case R.id.KEY_L:
			processInputL();
			break;
		case R.id.KEY_M:
			processInputM();
			break;
		case R.id.KEY_N:
			processInputN();
			break;
		case R.id.KEY_O:
			processInputO();
			break;
		case R.id.KEY_P:
			processInputP();
			break;
		case R.id.KEY_Q:
			processInputQ();
			break;
		case R.id.KEY_R:
			processInputR();
			break;
		case R.id.KEY_S:
			processInputS();
			break;
		case R.id.KEY_T:
			processInputT();
			break;
		case R.id.KEY_U:
			processInputU();
			break;
		case R.id.KEY_V:
			processInputV();
			break;
		case R.id.KEY_W:
			processInputW();
			break;
		case R.id.KEY_X:
			processInputX();
			break;
		case R.id.KEY_Y:
			processInputY();
			break;
		case R.id.KEY_Z:
			processInputZ();
			break;
		case R.id.KEY_DEL:
			delCurrentInput();
			break;
		case R.id.selecthandwriting:
			showHandWriting();
			break;
		case R.id.selectkeyboard:
			showKeyboard();
			break;
		case R.id.clean:
			resetHandWritingRecognizeClicked();
			break;
		}
	}

	void inputTest() {
		clearCurrentInput();
	}

	void processInputA() {
		processInput(new char[] { 'a' });
	}

	void processInputB() {
		processInput(new char[] { 'b' });
	}

	void processInputC() {
		processInput(new char[] { 'c' });
	}

	void processInputD() {
		processInput(new char[] { 'd' });
	}

	void processInputE() {
		processInput(new char[] { 'e' });
	}

	void processInputF() {
		processInput(new char[] { 'f' });
	}

	void processInputG() {
		processInput(new char[] { 'g' });
	}

	void processInputH() {
		processInput(new char[] { 'h' });
	}

	void processInputI() {
		processInput(new char[] { 'i' });
	}

	void processInputJ() {
		processInput(new char[] { 'j' });
	}

	void processInputK() {
		processInput(new char[] { 'k' });
	}

	void processInputL() {
		processInput(new char[] { 'l' });
	}

	void processInputM() {
		processInput(new char[] { 'm' });
	}

	void processInputN() {
		processInput(new char[] { 'n' });
	}

	void processInputO() {
		processInput(new char[] { 'o' });
	}

	void processInputP() {
		processInput(new char[] { 'p' });
	}

	void processInputQ() {
		processInput(new char[] { 'q' });
	}

	void processInputR() {
		processInput(new char[] { 'r' });
	}

	void processInputS() {
		processInput(new char[] { 's' });
	}

	void processInputT() {
		processInput(new char[] { 't' });
	}

	void processInputU() {
		processInput(new char[] { 'u' });
	}

	void processInputV() {
		processInput(new char[] { 'v' });
	}

	void processInputW() {
		processInput(new char[] { 'w' });
	}

	void processInputX() {
		processInput(new char[] { 'x' });
	}

	void processInputY() {
		processInput(new char[] { 'y' });
	}

	void processInputZ() {
		processInput(new char[] { 'z' });
	}

	void processInputSpace() {
		// if (currentPinYin.length() == 0) {
		// commitedText.append(" ");
		// currentInput.append(" ");
		// currentIndex++;
		// } else {
		// currentInput.append(currentPinYin);
		// commitedText.append(currentPinYin);
		// currentIndex += currentPinYin.length();
		// for (int i = 0; i < currentPinYin.length(); ++i) {
		// mOpenWnnZHCN.deleteBy1();
		// }
		// cleanCurrentPinyinView();
		//
		// }
		//
		// inputShow.setText(currentInput.toString());
		// mCandidateView.clear();

	}

	void delCurrentInput() {
		if (isHandWriting()) {
			delCurrentInputNoPinyin();
		} else {
			if (PinYinInput.getText().length() > 0) {
				ckManager.processDel();

			} else {
				delCurrentInputNoPinyin();
			}
		}
	}

	private void delCurrentInputNoPinyin() {
		if (currentInput.length() > 0) {
			currentInput.deleteCharAt(currentInput.length() - 1);
		}
		inputShow.setText(currentInput.toString());
	}

	void showHandWriting() {
		handWritingBoard.setVisibility(View.VISIBLE);
		keyboardGrid.setVisibility(View.GONE);
		ckManager.delAll();
		PinYinInput.setText("");
		mCandidateView.clear();

	}

	void showKeyboard() {
		handWritingBoard.setVisibility(View.GONE);
		keyboardGrid.setVisibility(View.VISIBLE);
		resetHandWritingRecognize();
		mCandidateView.clear();
	}

	void resetHandWritingRecognizeClicked() {
		resetHandWritingRecognize();
		mCandidateView.clear();
	}

	@Override
	public void candidateSelected(WnnWord wnnWord) {
		String candidate = null;
		if (wnnWord != null) {
			candidate = wnnWord.candidate;
		}
		if (!TextUtils.isEmpty(candidate)) {
			cleanCurrentPinyinView();
			appendCandidate(candidate);
			inputShow.setText(currentInput.toString());
		}
		// mOpenWnnZHCN.commitTextSelected(wnnWord);
		mCandidateView.clear();
		if (isHandWriting()) {
			resetHandWritingRecognize();
		} else {
			ckManager.candidateSelected(wnnWord);
		}
	}

	private void cleanCurrentPinyinView() {
		PinYinInput.setText("");
	}

	private void appendCandidate(String candidate) {
		currentInput.append(candidate);
		// currentIndex += candidate.length();
	}

	@Override
	public void handWritingRecognized(ArrayList<WnnWord> result) {
		mCandidateView.setSuggestions(result, false, false);
	}

	// TODO 整理一下

	private void resetHandWritingRecognize() {
		handWritingBoard.reset_recognize();
	}

	/*
	 * 删除和上屏都要区分手写和字母输入；
	 */
	private boolean isHandWriting() {
		return handWritingBoard.getVisibility() == View.VISIBLE;
	}

	@Override
	public void onPinyinQueryed(PinyinQueryResult pyQueryResult) {
		if (pyQueryResult != null) {
			mCandidateView.setSuggestions(pyQueryResult.getCandidateList(), false, false);
			String pinyin = pyQueryResult.getCurrentInput();
			updatePinyin(pinyin);
		}
	}

	private void updatePinyin(String pinyin) {
		PinYinInput.setText(pinyin);
	}
}
