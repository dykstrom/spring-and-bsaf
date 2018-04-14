/*
 * Copyright (C) 2017 Johan Dykstrom
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package se.dykstrom.spring;

import org.jdesktop.application.Action;
import org.jdesktop.application.*;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

@Component
public class BsafFrameView extends FrameView {

    private final BsafApplication application;

    private final SwingDialog dialog;

    @Inject
    public BsafFrameView(BsafApplication application, SwingDialog dialog) {
        super(application);
        this.application = application;
        this.dialog = dialog;
        init();
    }

    private void init() {
        ActionMap actions = application.getContext().getActionMap(this);

        JButton button = new JButton();
        button.setName("button");
        button.setAction(actions.get("openDialog"));

        JPanel panel = new JPanel(new BorderLayout());
        panel.setName("panel");
        panel.setBorder(new EmptyBorder(100, 150, 100, 150));
        panel.setPreferredSize(new Dimension(500, 300));
        panel.add(button, BorderLayout.CENTER);

        setComponent(panel);

        ResourceMap resources = application.getContext().getResourceMap(BsafFrameView.class);
        resources.injectComponents(panel);
    }

    @Action
    public void openDialog() {
        application.show(dialog);
    }
}
