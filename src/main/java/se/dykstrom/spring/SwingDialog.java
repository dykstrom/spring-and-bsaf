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

import org.jdesktop.application.ResourceMap;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

@Component
public class SwingDialog extends JDialog {

    private final BsafApplication application;

    @Inject
    public SwingDialog(BsafApplication application) {
        this.application = application;
        init();
    }

    private void init() {
        JLabel label = new JLabel();
        label.setName("label");

        JPanel panel = new JPanel(new BorderLayout());
        panel.setName("panel");
        panel.setBorder(new EmptyBorder(100, 150, 100, 150));
        panel.add(label, BorderLayout.CENTER);

        setName("dialog");
        add(panel, BorderLayout.CENTER);

        ResourceMap resources = application.getContext().getResourceMap(SwingDialog.class);
        resources.injectComponents(this);

        pack();
    }
}
