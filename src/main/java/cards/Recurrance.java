package chronomuncher.cards;

import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.GainStrengthPower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;

import chronomuncher.cards.MetricsCard;
import chronomuncher.ChronoMod;
import chronomuncher.patches.Enum;

import java.lang.Math;

public class Recurrance extends MetricsCard {
	public static final String ID = "Recurrance";
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;

	private static final int COST = 1;
	private static final int ATTACK_DMG = 3;
	private static final int HITS = 2;
	private static final int HITS_UPGRADE = 1;

	public Recurrance() {
		super(ID, NAME, "chrono_images/cards/Recurrance.png", COST, DESCRIPTION, AbstractCard.CardType.ATTACK,
				Enum.CHRONO_GOLD, AbstractCard.CardRarity.UNCOMMON,
				AbstractCard.CardTarget.ENEMY);

		this.baseDamage = ATTACK_DMG;
		this.baseMagicNumber = HITS;
		this.magicNumber = this.baseMagicNumber;
	}

	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {

		for (int hit = 0; hit < this.magicNumber; hit++) {
			AbstractDungeon.actionManager.addToBottom(
				new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_LIGHT));
		}

		// if (this.damage > 0) {
		// 	int unblockedHits = this.magicNumber - (int)Math.floor(m.currentBlock / this.damage);

		// 	if (unblockedHits < 1) { return; }

			// HITS WERE UNBLOCKED, SO ADD SHIT TO STACKS
		    for (AbstractPower pow : m.powers) {
				if (pow.type == AbstractPower.PowerType.DEBUFF) {
					if (pow.canGoNegative == true) {
						AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, p, pow, -this.magicNumber, true));
					} else if (pow.ID == "Shackled") {
						AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, p, pow, -this.magicNumber, true));
					} else if (pow.ID.contains("DelayedAttack")) {
						AbstractDungeon.actionManager.addToBottom(new ReducePowerAction(m, p, pow, this.magicNumber));
					} else {
						AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, p, pow, this.magicNumber, true));
					}
				}
		    }
		// }
	}

	@Override
	public AbstractCard makeCopy() {
		return new Recurrance();
	}

	@Override
	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			upgradeDamage(HITS_UPGRADE);
			upgradeMagicNumber(HITS_UPGRADE);
		}
	}
}