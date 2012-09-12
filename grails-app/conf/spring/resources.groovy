// Switch do Czech locale as default
beans = {
    localeResolver(org.springframework.web.servlet.i18n.FixedLocaleResolver) {
        defaultLocale = new Locale("cs", "CZ")
    }
}