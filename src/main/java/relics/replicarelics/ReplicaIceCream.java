package chronomuncher.relics;

import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.FocusPower;

import com.badlogic.gdx.graphics.Texture;
import basemod.abstracts.CustomRelic;
import chronomuncher.ChronoMod;
import chronomuncher.orbs.*;
import chronomuncher.actions.*;

public class ReplicaIceCream extends CustomRelic {
    public static final String ID = "IceCream?";

    public ReplicaIceCream() {
        super(ID, new Texture("chrono_images/relics/IceCream.png"), new Texture("chrono_images/relics/outline/IceCream.png"), RelicTier.SPECIAL, LandingSound.CLINK);
    }

    @Override
    public String getUpdatedDescription() { return this.DESCRIPTIONS[0]; }
    
    @Override
    public void atBattleStart() {
        flash();
        AbstractDungeon.player.energy.use(1);
        AbstractDungeon.actionManager.addToBottom(new ChronoChannelAction(new UnlockedIceCream(false)));
    }

    @Override
    public AbstractRelic makeCopy() { return new ReplicaIceCream(); }
}