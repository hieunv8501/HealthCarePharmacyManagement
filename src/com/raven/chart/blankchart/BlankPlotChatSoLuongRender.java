package com.raven.chart.blankchart;

import java.awt.Graphics2D;

public abstract class BlankPlotChatSoLuongRender {

    public abstract String getLabelText(int index);

    public abstract void renderSeries(BlankPlotChartSoLuong chart, Graphics2D g2, SeriesSize size, int index);
}
