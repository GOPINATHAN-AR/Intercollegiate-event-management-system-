                </section>                
            </div>

            <footer>
                Copyright &copy; <span class="tm-current-year">2018</span> Inter-Collegiate Meet - Designed by Tooplate
            </footer>    
        </div>
        
        <!-- load JS files -->
        <script src="js/jquery-1.11.3.min.js"></script>
        <script src="js/popper.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script>
            $(document).ready(function(){                
                // Update the current year in copyright
                $('.tm-current-year').text(new Date().getFullYear());
            });
        </script>
</body>
</html>