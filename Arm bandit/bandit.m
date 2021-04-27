function [reward] = bandit(action)
m = [.1 .2 .25 .3 .35 .4 .5 .7 .45 .55]; % Mean rewards
reward = m(action)+randn;
endfunction
