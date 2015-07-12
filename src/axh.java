/**
 * GuiDisconnected
 */

import java.util.Iterator;
import java.util.List;

public class axh extends axu {
    private String a;
    private eu f;
    private List<String> g;
    private final axu h;
    private int i;

    public axh(axu var, String var1, eu var2) {
        this.h = var;
        this.a = bnq.a(var1);
        this.f = var2;
    }

    /**
     * Key Typed
     * Überschrieben, damit nicht ESC gedrückt werden kann
     *
     * @param ch   Der char, der gedrückt wurde.
     * @param code Der keycode des chars, der gedrückt wurde.
     */
    protected void a(char ch, int code) {
    }

    /**
     * Init GUI
     */
    public void b() {
        this.n.clear();
        this.g = this.q.c(this.f.d(), this.l - 50);
        this.i = this.g.size() * this.q.a;
        this.n.add(new avs(0, this.l / 2 - 100, this.m / 2 + this.i / 2 + this.q.a, bnq.a("gui.toMenu")));

        // sofort wieder connecten indem GuiConnecting geöffnet wird.
        if (j.D() != null)
            j.a(new awz(h, j, j.D(), f.d()));
    }

    /**
     * Action Performed
     *
     * @param button Der Button, der gedrückt wurde.
     */
    protected void a(avs button) {
        if (button.k == 0) {
            this.j.a(this.h);
        }

    }

    /**
     * Draw Screen
     *
     * @param mouseX       Mouse x-Koordinate
     * @param mouseY       Mouse y-Koordinate
     * @param partialTicks partialTicks
     */
    public void a(int mouseX, int mouseY, float partialTicks) {
        this.c();
        this.a(this.q, this.a, this.l / 2, this.m / 2 - this.i / 2 - this.q.a * 2, 11184810);
        int var3 = this.m / 2 - this.i / 2;
        if (this.g != null) {
            for (Iterator var4 = this.g.iterator(); var4.hasNext(); var3 += this.q.a) {
                String var5 = (String) var4.next();
                this.a(this.q, var5, this.l / 2, var3, 16777215);
            }
        }

        super.a(mouseX, mouseY, partialTicks);
    }
}
