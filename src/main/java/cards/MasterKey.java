package chronomuncher.cards;

import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.GameActionManager;
import chronomuncher.actions.*;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import java.util.ArrayList;

import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.powers.FocusPower;

import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.relics.*;

import chronomuncher.cards.MetricsCard;
import chronomuncher.ChronoMod;
import chronomuncher.patches.Enum;
import chronomuncher.orbs.*;
import chronomuncher.actions.PlayFreePowersAction;

public class MasterKey extends MetricsCard {
	public static final String ID = "MasterKey";
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	public static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;

	private static final int COST = 1;

	public MasterKey() {
		super(ID, NAME, "images/cards/MasterKey.png", COST, DESCRIPTION, AbstractCard.CardType.SKILL,
				Enum.BRONZE, AbstractCard.CardRarity.RARE, AbstractCard.CardTarget.SELF);

		this.exhaust = true;
		this.retain = true;
	}

	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {
		AbstractDungeon.actionManager.addToBottom(new PlayFreePowersAction(this.upgraded));
   	}

	public void onMoveToDiscard() {
		super.onMoveToDiscard();
		this.costForTurn = this.cost;
		this.isCostModifiedForTurn = false;
	}

	@Override
	public void atTurnStart() {
		this.retain = true;
	}

	@Override
	public AbstractCard makeCopy() {
		return new MasterKey();
	}

	@Override
	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
      		this.rawDescription = UPGRADE_DESCRIPTION;
   		   	initializeDescription();
		}
	}
}