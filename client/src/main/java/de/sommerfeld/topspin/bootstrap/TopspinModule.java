package de.sommerfeld.topspin.bootstrap;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import de.sommerfeld.topspin.export.ExportService;
import de.sommerfeld.topspin.export.PdfExportService;
import de.sommerfeld.topspin.fx.view.ViewLoader;
import de.sommerfeld.topspin.fx.view.ViewProvider;

public class TopspinModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(Bootstrap.class).to(TopspinBootstrap.class);
        bind(ExportService.class).to(PdfExportService.class);

        bind(ViewProvider.class).asEagerSingleton();
        bind(ViewLoader.class).in(Singleton.class);
    }
}
